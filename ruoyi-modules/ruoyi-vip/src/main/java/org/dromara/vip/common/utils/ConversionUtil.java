package org.dromara.vip.common.utils;

/**
 * 转换器
 */
public class ConversionUtil {
    private static final String BASE32_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUV";
    private static final String BASE64_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_@";

    /**
     * 十进制转64进制
     * @param decimal
     * @return
     */
    public static String numberToBase64(Integer decimal) {
        return numberToBase64(decimal.longValue());
    }

    /**
     * 十进制转64进制
     * @param decimal
     * @return
     */
    public static String numberToBase64(Long decimal) {
        StringBuilder base64 = new StringBuilder();
        while (decimal > 0) {
            long remainder = decimal % 64;
            base64.insert(0, BASE64_CHARS.charAt((int) remainder));
            decimal /= 64;
        }
        return base64.toString();
    }

    /**
     * 十进制转32进制
     * @param decimal
     * @return
     */
    public static String numberToBase32(Integer decimal) {
        return numberToBase32(decimal.longValue());
    }

    /**
     * 十进制转32进制
     * @param decimal
     * @return
     */
    public static String numberToBase32(Long decimal) {
        StringBuilder base32 = new StringBuilder();
        while (decimal > 0) {
            long remainder = decimal % 32;
            base32.insert(0, BASE32_CHARS.charAt((int) remainder));
            decimal /= 32;
        }
        return base32.toString();
    }
}
