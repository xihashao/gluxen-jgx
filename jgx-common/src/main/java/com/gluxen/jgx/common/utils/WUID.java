package com.gluxen.jgx.common.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 无中心化的分布式全局唯一ID<br>
 * <br>
 * WUID长度为11个byte(转换成HEX字符串为22个字符)，由3段构成：<br>
 * 1、前面4个byte(8个HEX字符)存储的是从2010-01-01 00:00:00 000到现在的秒数;<br>
 * 2、中间4个byte(8个HEX字符)存储的是机器网卡的hash code + 进程号的hash code组合的编码;<br>
 * 3、最后3个byte(6个HEX字符)存储的是每一秒重置一次的计数器值;<br>
 * <br>
 * 因为WUID以时间戳开头的，所以它是近似有序的，如果使用WUID代替UUID做数据库主键，索引插入效率会高很多。<br>
 * 在同一个进程中每秒最多可以创建16,777,216个不同的WUID。
 * 
 * @author cby
 * 
 */
public class WUID {
    public static final int WUID_HEX_STR_LENGTH = 22;
    private static final Logger LOG = Logger.getLogger(WUID.class);

    private static final int _INSTANCE;
    private static int _TIMESTAMP;
    private static AtomicInteger _COUNTER = new AtomicInteger(0);

    private static final ReentrantReadWriteLock _LOCK = new ReentrantReadWriteLock();

    static {
        StringBuilder sb = new StringBuilder();

        try {
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface ni = e.nextElement();
                sb.append(ni.toString());
            }
        } catch (IOException e) {
            LOG.error("Init the WUID's _INSTANCE error", e);
            throw new RuntimeException(e);
        }

        int machinePiece = Math.abs(sb.toString().hashCode()) << 16;
        int processPiece = Math.abs(java.lang.management.ManagementFactory.getRuntimeMXBean().getName().hashCode()) & 0xFFFF;

        _INSTANCE = machinePiece | processPiece;

        LOG.info("WUID init the _INSTANCE with[" + Integer.toHexString(_INSTANCE) + "]");

        // 2010-01-01 00:00:00 000的毫秒数
        long start = 1262275200000L;
        // timestamp相当于从2010-01-01 00:00:00 000 到现在的秒数
        _TIMESTAMP = (int) ((new Date().getTime() - start) / 1000L);

        // 每秒更新timestamp和counter
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        LOG.error("Update the WUID's _TIMESTAMP error", e);
                        throw new RuntimeException(e);
                    }

                    try {
                        _LOCK.writeLock().lock();
                        _TIMESTAMP = _TIMESTAMP + 1;
                        _COUNTER = new AtomicInteger(0);
                    } finally {
                        _LOCK.writeLock().unlock();
                    }
                }
            }
        }, "WUIDTimeStampUpdateThread").start();
    }

    private int timestamp;
    private int instance;
    private int counter;

    /**
     * 构建一个WUID对象
     * 
     * @param timestamp
     *            时间戳
     * @param instance
     *            主机+进程的组合
     * @param counter
     *            计数器值
     */
    private WUID(int timestamp, int instance, int counter) {
        this.timestamp = timestamp;
        this.instance = instance;
        this.counter = counter;
    }

    /**
     * 创建一个新的WUID对象
     * 
     * @return WUID
     */
    public static WUID newWUID() {
        try {
            _LOCK.readLock().lock();

            int t = _TIMESTAMP;
            int c = _COUNTER.incrementAndGet();

            return new WUID(t, _INSTANCE, c);
        } finally {
            _LOCK.readLock().unlock();
        }
    }

    /**
     * 从WUID的16进制字符串转换成WUID
     * 
     * @param wuidHexStr
     *            WUID字符串
     * @return WUID
     */
    public static WUID fromHexStr(String wuidHexStr) {
        if (wuidHexStr == null) {
            throw new IllegalArgumentException("wuid hex string must not null");
        }

        if (wuidHexStr.length() != WUID_HEX_STR_LENGTH) {
            throw new IllegalArgumentException("juid hex string's length must be " + WUID_HEX_STR_LENGTH);
        }

        try {
            byte[] bb = new byte[WUID_HEX_STR_LENGTH / 2];

            for (int i = 0; i < bb.length; i++) {
                bb[i] = (byte) Integer.parseInt(wuidHexStr.substring(i * 2, i * 2 + 2), 16);
            }

            return fromBytes(bb);
        } catch (NumberFormatException e) {
            throw new WUIDFormatException("Format WUID hex string error", e);
        }
    }
    
    public static WUID fromBytes(byte[] bb) {
        if (bb == null) {
            throw new IllegalArgumentException("bytes must not null");
        }

        if (bb.length != (WUID_HEX_STR_LENGTH / 2)) {
            throw new IllegalArgumentException("bytes length must be " + (WUID_HEX_STR_LENGTH / 2));
        }

        try {
            int t = (int) ((((bb[0] & 0xff) << 24) | ((bb[1] & 0xff) << 16) | ((bb[2] & 0xff) << 8) | ((bb[3] & 0xff) << 0)));
            int i = (int) ((((bb[4] & 0xff) << 24) | ((bb[5] & 0xff) << 16) | ((bb[6] & 0xff) << 8) | ((bb[7] & 0xff) << 0)));
            int c = (int) (((((byte)0 & 0xff) << 24) | ((bb[8] & 0xff) << 16) | ((bb[9] & 0xff) << 8) | ((bb[10] & 0xff) << 0)));

            return new WUID(t, i, c);
        } catch (NumberFormatException e) {
            throw new WUIDFormatException("Format bytes error", e);
        }
    }
    
    public static byte[] convertHexStr2Bytes(String wuidHexStr) {
        if (wuidHexStr == null) {
            throw new IllegalArgumentException("juid hex string must not null");
        }

        if (wuidHexStr.length() != WUID_HEX_STR_LENGTH) {
            throw new IllegalArgumentException("juid hex string's length must be " + WUID_HEX_STR_LENGTH);
        }

        try {
            byte[] bb = new byte[WUID_HEX_STR_LENGTH / 2];

            for (int i = 0; i < bb.length; i++) {
                bb[i] = (byte) Integer.parseInt(wuidHexStr.substring(i * 2, i * 2 + 2), 16);
            }

            return bb;
        } catch (NumberFormatException e) {
            throw new WUIDFormatException("Format WUID hex string error", e);
        }
    }

    /**
     * 输出WUID的byte数组
     * 
     * @return WUID的byte数组
     */
    public byte[] toBytes() {
        byte[] b = new byte[11];

        b[0] = (byte) (timestamp >> 24);
        b[1] = (byte) (timestamp >> 16);
        b[2] = (byte) (timestamp >> 8);
        b[3] = (byte) (timestamp >> 0);
        
        b[4] = (byte) (instance >> 24);
        b[5] = (byte) (instance >> 16);
        b[6] = (byte) (instance >> 8);
        b[7] = (byte) (instance >> 0);

        // 只输出counter低位上的3个byte，因为设计中最高位的一个byte是0，不需要转换
        b[8] = (byte) (counter >> 16);
        b[9] = (byte) (counter >> 8);
        b[10] = (byte) (counter >> 0);

        return b;
    }

    /**
     * 输出WUID 16进制的字符串
     */
    @Override
    public String toString() {
        byte[] b = toBytes();
        StringBuilder buf = new StringBuilder(22);

        for (int i = 0; i < b.length; i++) {
            int x = b[i] & 0xFF;
            String s = Integer.toHexString(x);

            if (s.length() == 1) {
                buf.append("0");
            }

            buf.append(s);
        }

        return buf.toString();
    }

    /**
     * 转成WUID原始的存储内容字符串，方便调试
     * 
     * @return 转成JUID原始的存储内容字符串
     */
    public String rawString() {
        return "[timestamp=" + timestamp + ", instance=" + instance + ", counter=" + counter + "]";
    }
}

/**
 * WUID字符串转换异常
 * 
 * @author cby
 * 
 */
class WUIDFormatException extends RuntimeException {
    private static final long serialVersionUID = -8151413230773546638L;

    public WUIDFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}