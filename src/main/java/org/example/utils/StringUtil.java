package org.example.utils;

/**
 * StoreManagementSystem
 * 字符串处理
 *
 * @author MaxBrooks 15905898514@163.com
 * @version 2023/6/12 10:27
 * @since JDK17
 */

public class StringUtil {

    /**
     * 判读字符串是否为空
     * @param str 待判断字符串
     * @return 结果值
     */
    public static boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
    }

    /**
     * 判读字符串是否不为空
     * @param str 待判断字符串
     * @return 结果值
     */
    public static boolean isNotEmpty(String str){
        return str != null && !"".equals(str.trim());
    }


}
