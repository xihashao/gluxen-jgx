package com.gluxen.jgx.util;

/**
 * Ttitle: URL格式化<br>
 * Description:对url格式化，用于把 url作为参数传递<br>
* @author lishiqiang
* @date 2017-3-15
* modify history
 */
public class UrlUtil {
    /**
     * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
     */
    private UrlUtil() {
    }

    private static final char[] str1 = {
        '~', '^', '`', '*'};
    private static final char[] str2 = {
        '=', '%', '?', '&'};

    /**
     * 转换url
     *
     * @param url
     * @return
     */
    public static String setUrl(String url) {
        if (url == null || url.trim().length() == 0) return "";
        int i, m;
        for (m = 0; m < str2.length; m++) {
            i = url.indexOf(str2[m]);
            while (i > 0) {
                url = url.substring(0, i) + str1[m] + url.substring(i + 1);
                i = url.indexOf(str2[m]);
            }

        }
        return url;
    }

    /**
     * 转换回str
     *
     * @param url
     * @return
     */
    public static String getUrl(String url) {
        if (url == null || url.trim().length() == 0) return "";
        int i, m;
        for (m = 0; m < str1.length; m++) {
            i = url.indexOf(str1[m]);
            while (i > 0) {
                url = url.substring(0, i) + str2[m] + url.substring(i + 1);
                i = url.indexOf(str1[m]);
            }

        }
        return url;
    }
}
