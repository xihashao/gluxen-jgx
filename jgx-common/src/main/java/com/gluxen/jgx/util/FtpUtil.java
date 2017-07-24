package com.gluxen.jgx.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ftp处理类
* @author lishiqiang
* @date 2017-3-15
* modify history
 */
public class FtpUtil {

    private static Logger logger = Logger.getLogger(FtpUtil.class);

    public FTPClient ftpClient;

    public boolean connectServer(String host,int port,String user,String pwd,String path) {
        ftpClient = new FTPClient();
//		ftpClient.setControlEncoding("GBK");
        logger.debug("准备连接到服务器：" + host);
        try {
            ftpClient.connect(host, port);
            int replyCode = ftpClient.getReplyCode();
            logger.debug(replyCode);
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                ftpClient.disconnect();
                logger.debug("服务器拒绝连接...");
                return false;
            }
            logger.debug("连接成功，准备登录服务器...");
            ftpClient.login(user, pwd);
            //ftpClient.setDefaultTimeout(60000);       //连接超时为60秒
            //ftpClient.setDataTimeout(60000);       //设置传输超时时间为60秒
            logger.debug("登录成功，检查工作路径...");
            // Path is the sub-path of the FTP path
            if (!StringUtils.isEmpty(path)) {
                if (!ftpClient.changeWorkingDirectory(path)) {
                    logger.debug("找不到指定路径...");
                    ftpClient.logout();
                    ftpClient.disconnect();
                    return false;
                } else {
                    logger.debug("当前工作路径：" + path);
                    return true;
                }
            }
            logger.debug("当前工作路径为默认路径...");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                disConnectServer();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return false;
        }
        return true;
    }

    public void disConnectServer() throws IOException {
        if (ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    public FTPFile[] getFiles(String filterFilename) {

        FTPFile[] files = null;
        try {
            files = ftpClient.listFiles(filterFilename);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                disConnectServer();
            } catch (IOException e1) {
                e1.printStackTrace();
                return null;
            }
            return null;
        }

        return files;

    }

    public FTPFile getFile(String fileName) {
//		FTPListParseEngine engine = null;
//		List<FTPFile> files = new ArrayList<FTPFile>();
        FTPFile[] files = null;
        try {
            files = ftpClient.listFiles(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                disConnectServer();
            } catch (IOException e1) {
                e1.printStackTrace();
                return null;
            }
            return null;
        }
//		try {
//			engine = ftpClient.initiateListParsing(dirPath);
//			FTPFile[] tempfiles = null;
//			Date createTime = null;
//			Date endCreateTime = null;
//			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//			SimpleDateFormat simple1 = new SimpleDateFormat("yyyyMMddHHmm");
//			Date start = simple.parse(startTime);
//			Date end = simple.parse(endTime);
//			logger.debug("准备过滤所需文件...");
//			while (engine.hasNext()){
//				tempfiles = engine.getNext(20);
//				for(FTPFile file : tempfiles){
//					String name = file.getName();
//					createTime = simple1.parse(name.substring(name.indexOf("_")+1, name.indexOf("-")));
//					endCreateTime = simple1.parse(name.substring(name.indexOf("-")+1, name.lastIndexOf("_")));
//					if((createTime.after(start) || createTime.equals(start))
//							&& (endCreateTime.before(end) || endCreateTime.equals(end))){
//						logger.debug(file.getName());
//						files.add(file);
//					}
//				}
//			}
//		} catch (Exception e) {
//			logger.error("错误：============>" + e.getMessage());
//			try {
//				if(!Common.isEmpty(ftpClient) && ftpClient.isConnected()){
//					ftpClient.disconnect();
//				}
//			} catch (IOException e1) {
//				logger.error("错误：============>" + e1.getMessage());
//			}
//		}
        return files.length >= 1 ? files[0] : null;
    }

    //=======================================================================
    //==         About directory       =====
    // The following method using relative path better.
    //=======================================================================

    public boolean changeDirectory(String path) throws IOException {
        return ftpClient.changeWorkingDirectory(path);
    }

    public boolean createDirectory(String pathName) throws IOException {
        return ftpClient.makeDirectory(pathName);
    }

    public boolean removeDirectory(String path) throws IOException {
        return ftpClient.removeDirectory(path);
    }

    // delete all subDirectory and files.
    public boolean removeDirectory(String path, boolean isAll)
            throws IOException {

        if (!isAll) {
            return removeDirectory(path);
        }

        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr == null || ftpFileArr.length == 0) {
            return removeDirectory(path);
        }
        //
        for (FTPFile ftpFile : ftpFileArr) {
            if (!ftpFile.getName().equals(".") && (!ftpFile.getName().equals(".."))) {

                String name = ftpFile.getName();
                if (ftpFile.isDirectory()) {
                    System.out.println("* [sD]Delete subPath [" + path + "/" + name + "]");
                    removeDirectory(path + "/" + name, true);
                } else if (ftpFile.isFile()) {
                    System.out.println("* [sF]Delete file [" + path + "/" + name + "]");
                    deleteFile(path + "/" + name);
                } else if (ftpFile.isSymbolicLink()) {

                } else if (ftpFile.isUnknown()) {

                }

            }
        }
        return ftpClient.removeDirectory(path);
    }

    // Check the path is exist; exist return true, else false.
    public boolean existDirectory(String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        for (FTPFile ftpFile : ftpFileArr) {
            if (ftpFile.isDirectory()
                    && ftpFile.getName().equalsIgnoreCase(path)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    //=======================================================================
    //==         About file        =====
    // Download and Upload file using
    // ftpUtil.setFileType(FtpUtil.BINARY_FILE_TYPE) better!
    //=======================================================================

    // #1. list & delete operation
    // Not contains directory
    public List<String> getFileList(String path) throws IOException {
        // listFiles return contains directory and file, it's FTPFile instance
        // listNames() contains directory, so using following to filer directory.
        //String[] fileNameArr = ftpClient.listNames(path);
        FTPFile[] ftpFiles = ftpClient.listFiles(path);

        List<String> retList = new ArrayList<String>();
        if (ftpFiles == null || ftpFiles.length == 0) {
            return retList;
        }
        for (FTPFile ftpFile : ftpFiles) {
            if (ftpFile.isFile()) {
                retList.add(ftpFile.getName());
            }
        }
        return retList;
    }

    public boolean deleteFile(String pathName) throws IOException {
        return ftpClient.deleteFile(pathName);
    }

    // #2. upload to ftp server
    // InputStream <------> byte[]  simple and See API

    public boolean uploadFile(String fileName, String newName)
            throws IOException {
        boolean flag = false;
        InputStream iStream = null;
        try {
            iStream = new FileInputStream(fileName);
            flag = ftpClient.storeFile(newName, iStream);
        } catch (IOException e) {
            flag = false;
            return flag;
        } finally {
            if (iStream != null) {
                iStream.close();
            }
        }
        return flag;
    }

    public boolean uploadFile(String fileName) throws IOException {
        return uploadFile(fileName, fileName);
    }

    public boolean uploadFile(InputStream iStream, String newName)
            throws IOException {
        boolean flag = false;
        try {
            // can execute [OutputStream storeFileStream(String remote)]
            // Above method return's value is the local file stream.
            flag = ftpClient.storeFile(newName, iStream);
        } catch (IOException e) {
            flag = false;
            return flag;
        } finally {
            if (iStream != null) {
                iStream.close();
            }
        }
        return flag;
    }

    // #3. Down load

    public boolean download(String remoteFileName, String localFileName)
            throws IOException {
        boolean flag = false;
        File outfile = new File(localFileName);
        OutputStream oStream = null;
        try {
            oStream = new FileOutputStream(outfile);
            flag = ftpClient.retrieveFile(remoteFileName, oStream);
        } catch (IOException e) {
            flag = false;
            return flag;
        } finally {
            oStream.close();
        }
        return flag;
    }

    public InputStream downFile(String sourceFileName) throws IOException {
        return ftpClient.retrieveFileStream(sourceFileName);
    }

}
