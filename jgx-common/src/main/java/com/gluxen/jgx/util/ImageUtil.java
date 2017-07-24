package com.gluxen.jgx.util;//package com.wisesoft.wisdom.trip.mall.util;
//
//import javax.swing.ImageIcon;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.awt.image.AffineTransformOp;
//import java.awt.geom.AffineTransform;
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.Color;
//import java.io.File;
//import java.io.BufferedOutputStream;
//import java.io.FileOutputStream;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//import com.sun.image.codec.jpeg.JPEGCodec;
//
///**
// * 图片处理，对图片缩放.<br>
// * 注：只能对jpg,jpeg格式图片进行处理
//* @author lishiqiang
//* @date 2017-3-15
//* modify history
// */
//public class ImageUtil {
//    ImageIcon originImage = null;
//    private String originFilePathName = null;
//    private String originFileName = null;
//    private String originFilePath = null;
//    private String originFileExtendName = null;
//    private static final String[] IMAGETYPE = {"jpeg", "jpg"};
//    private double originImageWidth = 0;
//    private double originImageHeight = 0;
//    private double newImageWidth = 0;
//    private double newImageHeight = 0;
//    private double[] scales = new double[2];
//    private int scaleType = 0;
//    private static final int SCALETYPE1 = 1;//1 不等比缩放指定缩放比例 调用：genScaleImageInMemory(scalex, scaley);
//    private static final int SCALETYPE2 = 2;//2 等比缩放指定缩放比例 调用：genScaleImageInMemory(scale, scale);
//    private static final int SCALETYPE3 = 3;//3 以x轴为基准缩放到指定宽度 公式：缩放比例 = 指定宽度 / 原图宽度   调用：genScaleImageInMemory(scalex, scalex);
//    private static final int SCALETYPE4 = 4;//4 以y轴为基准缩放到指定高度 公式：缩放比例 = 指定高度 / 原图高度   调用：genScaleImageInMemory(scaley, scaley);
//    private static final int SCALETYPE5 = 5;//5 缩放到指定尺寸  公式：x轴缩放比例 = 指定宽度 / 原图宽度
//    private static final int SCALETYPE6 = 6;//5 等比缩放指定缩放比例,以较小单位为基准
//
//    private java.lang.String serverRealPath;//服务器路径
//    private java.lang.String saveToDBFilename;
//    // y轴缩放比例 = 指定高度 / 原图高度 genScaleImageInMemory(scalex, scaley);
//
//    public ImageUtil(String originFilePathName) throws Exception {
//        if (isLegalImage(originFilePathName)) {
//            this.originFilePathName = originFilePathName;
//            parseOriginFileName(originFilePathName);
//            parseOriginFilePath(originFilePathName);
//            originImage = new ImageIcon(originFilePathName);
//            originImageWidth = originImage.getIconWidth();
//            originImageHeight = originImage.getIconHeight();
//        } else {
//            throw new Exception("file format erro.");
//        }
//    }
//    //y轴缩放比例 = 指定高度
//    // 原图高度 genScaleImageInMemory(scalex, scaley);
//
//    public ImageUtil(String originFilePathName, String serverpath) throws Exception {
//        if (isLegalImage(originFilePathName)) {
//            this.originFilePathName = originFilePathName;
//            parseOriginFileName(originFilePathName);
//            parseOriginFilePath(originFilePathName);
//            originImage = new ImageIcon(originFilePathName);
//            originImageWidth = originImage.getIconWidth();
//            originImageHeight = originImage.getIconHeight();
//            this.setServerRealPath(serverpath);
//        } else {
//            throw new Exception("file format err.");
//        }
//    }
//
//    /**
//     * 计算比例
//     *
//     * @param scaleType
//     * @param param1
//     * @param param2
//     */
//    private void calculateScale(int scaleType, double param1, double param2) {
//        double tempScaleX = 0;
//        double tempScaleY = 0;
//        setScaleType(scaleType);
//        switch (scaleType) {
//            case SCALETYPE1://1 不等比缩放指定缩放比例 调用：genScaleImageInMemory(scalex, scaley);
//                setScaleX(param1);
//                setScaleY(param2);
//                break;
//            case SCALETYPE2: //2 等比缩放指定缩放比例 调用：genScaleImageInMemory(scale, scale);
//                double param = param1 > param2 ? param2 : param1;
//                setScaleX(param);
//                setScaleY(param);
//                break;
//            case SCALETYPE3: //3 以x轴为基准等比缩放到指定宽度 公式：缩放比例 = 指定宽度 / 原图宽度   调用：genScaleImageInMemory(scalex, scalex);
//                tempScaleX = param1 / getOriginImageWidth();
//                if (tempScaleX > 0) {
//                    setScaleX(tempScaleX);
//                    setScaleY(tempScaleX);
//                } else {
//                    setScaleX(0.5);
//                    setScaleY(0.5);
//                }
//                break;
//            case SCALETYPE4://4 以y轴为基准等比缩放到指定高度 公式：缩放比例 = 指定高度 / 原图高度   调用：genScaleImageInMemory(scaley, scaley);
//                tempScaleY = param1 / getOriginImageHeight();
//                if (tempScaleY > 0) {
//                    setScaleY(tempScaleY);
//                    setScaleX(tempScaleY);
//                } else {
//                    setScaleX(0.5);
//                    setScaleY(0.5);
//                }
//                break;
//            case SCALETYPE5: //5 缩放到指定尺寸  公式：x轴缩放比例 = 指定宽度 / 原图宽度
//                tempScaleX = param1 / getOriginImageWidth();
//                tempScaleY = param2 / getOriginImageHeight();
//                if (tempScaleX > 0 && tempScaleY > 0) {
//                    setScaleX(tempScaleX);
//                    setScaleY(tempScaleY);
//                } else {
//                    setScaleX(0.5);
//                    setScaleY(0.5);
//                }
//                break;
//            case SCALETYPE6: //6 等比缩放指定缩放比例 选择较小单位为基准
//                tempScaleX = param1 / getOriginImageWidth();
//                tempScaleY = param2 / getOriginImageHeight();
//                param = tempScaleX > tempScaleY ? tempScaleY : tempScaleX;
//                setScaleX(param);
//                setScaleY(param);
//                break;
//            default :
//                setScaleX(0.5);
//                setScaleY(0.5);
//                break;
//        }
//    }
//
//    /**
//     * 创建图片
//     *
//     * @param scaleType
//     * @param param1
//     * @param param2
//     * @param version
//     */
//    public void createNewImage(int scaleType, double param1, double param2, String version) {
//        try {
//            calculateScale(scaleType, param1, param2);
//            BufferedImage bi = genScaleImageInMemory(scales[0], scales[1]);
//            if (version.equals("1.3")) {
//                String tempnewfile = this.getOriginFileName() + "_small";
//                String pathcomplete = this.getOriginFilePath() + tempnewfile + "." + getOriginFileExtendName();
//                System.out.println("new path" + pathcomplete);
//                this.setSaveToDBFilename(pathcomplete);
//                BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(new File(pathcomplete)));
//                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);
//                encoder.encode(bi);
//                output.close();
//            }
//        } catch (IOException e) {
//            System.out.println("scale Image erro: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("scale Image erro: " + e.getMessage());
//        }
//    }
//
//    /**
//     * 创建图片
//     *
//     * @param scaleType
//     * @param param1
//     * @param param2
//     */
//    public void createNewImage(int scaleType, int param1, int param2) {
//        createNewImage(scaleType, param1, param2, "1.3");
//    }
//
//
//    public void createNewImage(int param1, int param2) {
//        createNewImage(1, param1, param2);
//    }
//
//    //放缩的方法
//    private BufferedImage genScaleImageInMemory(double scalex, double scaley) throws Exception {
//        Image im = originImage.getImage();
//        BufferedImage bi = toBufferedImage(im);
//        //缩放：按照维护图像的需要添加或减弱像素，从而改变图形的大小。缩放可以是成比例的也可以是不成比例的，
//        //如果为 X 轴和 Y 轴分别给予不同的缩放值，那就是不成比例的。如果缩放值大于 1，那么缩放会增加每个轴的大小，
//        //如果缩放值小于 1，那么缩放会收缩每个轴的大小,负缩放值会沿对应的轴翻转图形。
//        AffineTransform atf = new AffineTransform();
//        atf.scale(scalex, scaley);
//        AffineTransformOp atfop = new AffineTransformOp(atf, AffineTransformOp.TYPE_BILINEAR);
//        return toBufferedImage(atfop.filter(bi, null));
//    }
//
//    public double getNewImageHeight() {
//        return newImageHeight;
//    }
//
//    public double getNewImageWidth() {
//        return newImageWidth;
//    }
//
//    public String getOriginFileExtendName() {
//        return originFileExtendName;
//    }
//
//    public String getOriginFileName() {
//        return originFileName;
//    }
//
//    public String getOriginFilePath() {
//        return originFilePath;
//    }
//
//    public String getOriginFilePathName() {
//        return originFilePathName;
//    }
//
//    public double getOriginImageHeight() {
//        return originImageHeight;
//    }
//
//    public double getOriginImageWidth() {
//        return originImageWidth;
//    }
//
//    /**
//     * 此处插入方法描述。
//     * 创建日期：(2003-6-19 13:40:30)
//     *
//     * @return java.lang.String
//     */
//    public java.lang.String getSaveToDBFilename() {
//        return saveToDBFilename;
//    }
//
//    public String getSaveToDBString() {
//        return this.getSaveToDBFilename().substring(this.getServerRealPath().length(), this.getSaveToDBFilename().length()).replace('\\', '/');
//
//    }
//
//    public int getScaleType() {
//        return scaleType;
//    }
//
//    public double getScaleX() {
//        return scales[0];
//    }
//
//    public double getScaleY() {
//        return scales[1];
//    }
//
//    /**
//     * 此处插入方法描述。
//     * 创建日期：(2003-6-19 11:19:28)
//     *
//     * @return java.lang.String
//     */
//    public java.lang.String getServerRealPath() {
//        return serverRealPath;
//    }
//
//    private boolean isLegalImage(String originFilePathName) {
//        boolean isImage = false;
//        int indexOfLastDot = originFilePathName.lastIndexOf('.'); //取得路径中最后一个"."的索引值
//        //判断是否为合法图象类型
//        if (indexOfLastDot != -1 && indexOfLastDot < originFilePathName.length() - 1) {
//            String ext = originFilePathName.substring(indexOfLastDot + 1);
//            for (int i = 0; i < IMAGETYPE.length; i++) {
//                if (ext.toLowerCase().equals(IMAGETYPE[i])) {
//                    originFileExtendName = ext;
//                    isImage = true;
//                    break;
//                }
//            }
//        }
//        return isImage;
//    }
//
//    /**
//     * @param originFilePathName
//     */
//
//    private void parseOriginFileName(String originFilePathName) {
//        int indexOfLastDot = originFilePathName.lastIndexOf('.');
//        int pos = originFilePathName.lastIndexOf('/');
//        if (pos != -1) {
//            setOriginFileName(originFilePathName.substring(pos + 1, indexOfLastDot));
//        } else {
//            pos = originFilePathName.lastIndexOf('\\');
//            if (pos != -1) {
//                setOriginFileName(originFilePathName.substring(pos + 1, indexOfLastDot));
//            } else {
//                setOriginFileName(originFilePathName.substring(0, indexOfLastDot));
//            }
//        }
//    }
//
//    /**
//     * @param originFilePathName
//     * @throws Exception
//     */
//    private void parseOriginFilePath(String originFilePathName) throws Exception {
//        String path = StringUtil.replace(originFilePathName, "\\", "/");
//        int pos = originFilePathName.lastIndexOf("/");
//        if (pos != -1) {
//            setOriginFilePath(path.substring(0, pos) + "/");
//        } else {
//            throw new Exception("none path!");
//        }
//    }
//
//    public void setNewImageHeight(double h) {
//        newImageHeight = h;
//    }
//
//    public void setNewImageWidth(double w) {
//        newImageWidth = w;
//    }
//
//    private void setOriginFileName(String filename) {
//        originFileName = filename;
//    }
//
//    private void setOriginFilePath(String originFilePath) {
//        this.originFilePath = originFilePath;
//    }
//
//    /**
//     * 此处插入方法描述。
//     * 创建日期：(2003-6-19 13:40:30)
//     *
//     * @param newSaveToDBFilename java.lang.String
//     */
//    public void setSaveToDBFilename(java.lang.String newSaveToDBFilename) {
//        saveToDBFilename = newSaveToDBFilename;
//    }
//
//    public void setScaleType(int scaleType) {
//        this.scaleType = scaleType;
//    }
//
//    public void setScaleX(double scalex) {
//        scales[0] = scalex;
//    }
//
//    public void setScaleY(double scaley) {
//        scales[1] = scaley;
//    }
//
//    /**
//     * 此处插入方法描述。
//     * 创建日期：(2003-6-19 11:19:28)
//     *
//     * @param newServerRealPath java.lang.String
//     */
//    public void setServerRealPath(java.lang.String newServerRealPath) {
//        serverRealPath = newServerRealPath;
//    }
//
//    //获得一个BufferedImage对象
//    private BufferedImage toBufferedImage(Image imagec) {
//        // Create the buffered image.
//        BufferedImage bufferedImage = new BufferedImage(imagec.getWidth(null), imagec.getHeight(null), BufferedImage.TYPE_INT_RGB);
//        // Copy image to buffered image.
//        Graphics g = bufferedImage.createGraphics();
//        // Clear background and paint the image.
//        g.setColor(Color.white);
//        g.fillRect(0, 0, imagec.getWidth(null), imagec.getHeight(null));
//        g.drawImage(imagec, 0, 0, null);
//        g.dispose();
//        return bufferedImage;
//    }
//
//    /**
//     * 测试
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        try {
//            ImageUtil ih = new ImageUtil("c:\\testold.jpg");
//            ih.createNewImage(5, 150, 100);
////            String path = "http://www.aaa.com/images/news/base/2005/12/23/1135327636796.JPG";
////            System.out.println(ImageUtil.getSmallPicPath(path));
//        } catch (Exception e) {
//        }
//    }
//
//    /**
//     * 根据路经以得小图路经
//     *
//     * @param path
//     * @return
//     */
//    public static String getSmallPicPath(String path) {
//        if (path == null) path = "";
//        int indexOfLastDot = path.lastIndexOf(".");
//        if (indexOfLastDot != -1) {
//            String ext = path.substring(indexOfLastDot + 1);
//            if ("JPG".equals(ext.toUpperCase()) || "JPEG".equals(ext.toUpperCase())) {
//                String basepath = path.substring(0, indexOfLastDot);
//                return basepath + "_small." + ext;
//            }
//        }
//        return path;
//    }
//
//    /**
//     * 根据路经以得小图路经
//     *
//     * @param path
//     * @return
//     */
//    public static String getBigPicPath(String path) {
//        return path.replaceAll("_small.", ".");
//    }
//}
