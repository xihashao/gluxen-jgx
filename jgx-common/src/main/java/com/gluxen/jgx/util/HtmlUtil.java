package com.gluxen.jgx.util;


/**
 * 此类中封装一些常用的HTML代码转换与格式化输出
* @author lishiqiang
* @date 2017-3-15
* modify history
 */
public class HtmlUtil {
    /**
     * 将字符串格式化成 HTML 代码输出
     * 只转换特殊字符，适合于 HTML 中的表单区域
     *
     * @param str 要格式化的字符串
     * @return 格式化后的字符串
     */
    public static String toHtmlInput(String str) {
        if (str == null) return null;
        String html = new String(str);
        html = StringUtil.replace(html, "&", "&amp;");
        html = StringUtil.replace(html, "<", "&lt;");
        html = StringUtil.replace(html, ">", "&gt;");
        return html;
    }

    /**
     * 将字符串格式化成 HTML 代码输出
     * 除普通特殊字符外，还对空格、制表符和换行进行转换，
     * 以将内容格式化输出，
     * 适合于 HTML 中的显示输出
     *
     * @param str 要格式化的字符串
     * @return 格式化后的字符串
     */
    public static String toHtml(String str) {
        if (str == null) return "";
        String html = new String(str);
        html = toHtmlInput(html);
        html = StringUtil.replace(html, "\r\n", "\n");
        html = StringUtil.replace(html, "\n", "<br>\n");
        html = StringUtil.replace(html, "\t", "    ");
        html = StringUtil.replace(html, "  ", " &nbsp;");
        return html;
    }

    /**
     * @param str
     * @return String
     */
    public static String toHtmlRN(String str) {
        if (str == null) return "";
        String html = new String(str);
        html = StringUtil.replace(html, "\r\n", "\n");
        html = StringUtil.replace(html, "\n", "<br>\n");
        html = StringUtil.replace(html, "\t", "    ");
        html = StringUtil.replace(html, "  ", " &nbsp;");
        return html;
    }

    /**
     * 把关键字转换成其它色彩
     *
     * @param source
     * @param keyWords
     * @param showColor
     * @return String
     */
    public static String getFormatKeyWords(String source, String keyWords, String showColor) {
        String reWords = "<font color='" + showColor + "'>" + keyWords + "</font>";
        return StringUtil.replace(source, keyWords, reWords);
    }

//    /**
//     * 生成HTML
//     *
//     * @param uf
//     * @param html
//     * @return UploadFile
//     */
//    public static UploadFile createJspToHtml(UploadFile uf, String html) {
//        //System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu1111111111" + uf.getFileExt());
//        //System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu1111111111" + uf.getFileName());
//        //System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu1111111111" + uf.getModule_id());
//        //System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu1111111111" + uf.getSaveFileName());
//        //System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu1111111111" + uf.getSavePath());
//        //System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu1111111111" + uf.getViewPath());
//        //文件存放路径
//        String protDirPath = uf.getSavePath();
//        try {
//
//            //protDirPath = StringUtil.replace(protDirPath, "/", "\\");
//            //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//            //根据不同的项目名和文档类型  创建新的文件夹
//            int filelength = protDirPath.split("/").length;
//            String newFile = "";
//            for (int i = 0; i < filelength - 1; i++) {
//                newFile += protDirPath.split("/")[i].toString() + "/";
//            }
//            //System.out.println("newfile=================================" + newFile);
//
//            File file = new File(newFile);
//            if (!file.exists()) {
//                //System.out.println("根据项目名称创建文件夹===================" + newFile);
//                file.mkdirs();
//            }
//            File f = new File(protDirPath);
//            //System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+protDirPath);
//            long nowtime = new Date().getTime();
//            //判断要生成html文件是否已经存在
//            if (!f.exists()) {
//                //System.out.println("ccccccccccccccccccccccccccccccccccccccccccccccc");
//                f.createNewFile();
//                //System.out.println("ddddddddddddddddddddddddddddddddddddd");
//                //给HTML文件写入内容
//                FileOutputStream out = new FileOutputStream(f);
//                PrintStream ps = new PrintStream(out);
//                //System.out.println("html========================="+html);
//                ps.print(html);
//                ps.close();
//                System.out.println("正在创建页面" + uf.getViewPath());
//                //System.out.println("要转向的页面url====1/");
//            } else if ((nowtime - f.lastModified()) >= 1 * 60 * 1000) {
//                f.createNewFile();
//                FileOutputStream out = new FileOutputStream(f);
//                PrintStream ps = new PrintStream(out);
//                ps.print(html);
//                ps.close();
//                System.out.println("正在刷新页面" + uf.getViewPath());
//                //System.out.println("要转向的页面url====2/");
//            } else {
//                System.out.println("要转向的页面url====3/");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return uf;
//    }

}
