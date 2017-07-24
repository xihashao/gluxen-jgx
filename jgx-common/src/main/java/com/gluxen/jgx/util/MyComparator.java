package com.gluxen.jgx.util;

import java.util.Date;

/**
 *  对象比较器,常用类型、自定义对象均可比较
 *  其他类型的比较自行添加
 * @author lishiqiang
 * @date 2017-3-15
 * modify history
 */
public class MyComparator {

    public static java.util.Comparator getComparator() {
        return new java.util.Comparator() {

            public int compare(Object o1, Object o2) {
                if (o1 instanceof String) {
                    return compare((String) o1, (String) o2);
                } else if (o1 instanceof Integer) {
                    return compare((Integer) o1, (Integer) o2);
                } else if (o1 instanceof Long) {
                    return compare((Long) o1, (Long) o2);
                } else if (o1 instanceof Boolean) {
                    return compare((Boolean) o1, (Boolean) o2);
                } else if (o1 instanceof Date) {
                    return compare((Date) o1, (Date) o2);
                    //以下为自定义对象比较
                }else {
                    System.err.println("未找到适合的比较器");
                    return 1;
                }
            }

            public int compare(String o1, String o2) {
                if(o1==null && o2==null){
                    return 0;
                }
                if(o1==null && o2!=null){
                    return -1;
                }
                if(o1!=null && o2==null){
                    return -1;
                }

                int len1 = o1.length();
                int len2 = o2.length();
                int n = Math.min(len1, len2);
                char v1[] = o1.toCharArray();
                char v2[] = o2.toCharArray();
                int pos = 0;
                while (n-- != 0) {
                    char c1 = v1[pos];
                    char c2 = v2[pos];
                    if (c1 != c2) {
                        return c1 - c2;
                    }
                    pos++;
                }
                return len1 - len2;
            }

            public int compare(Integer o1, Integer o2) {
                int val1 = o1.intValue();
                int val2 = o2.intValue();
                return (val1 < val2 ? -1 : (val1 == val2 ? 0 : 1));
            }

            public int compare(Long o1, Long o2) {
                long val1 = o1.longValue();
                long val2 = o2.longValue();
                return (val1 < val2 ? -1 : (val1 == val2 ? 0 : 1));
            }

            public int compare(Boolean o1, Boolean o2) {
                return (o1 == o2 ? 0 : (o1 == true ? 1 : -1));
            }

            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }

        };
    }
}
