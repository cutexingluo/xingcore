package top.cutexingluo.core.utils.se.character;

/**
 * 字符串核心工具类
 * <p>base hutool</p>
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 12:00
 */
public class StrCoreUtil {


    /**
     * 字符串是否为空
     *
     * @param str 被检测的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 字符串是否为非空
     * @param str 待检测的字符串
     * @return 是否为非空
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }


    /**
     * 判断字符串是否为空白
     * @param str 待检测的字符串
     * @return 是否为空白
     */
    public static boolean isBlank(CharSequence str) {
        final int length;
        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            // 只要有一个非空字符即为非空字符串
            if (!CharCoreUtil.isBlankChar(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检测字符串是否不为空白
     * @param str 待检测的字符串
     * @return 是否不为空白
     */
    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }




}
