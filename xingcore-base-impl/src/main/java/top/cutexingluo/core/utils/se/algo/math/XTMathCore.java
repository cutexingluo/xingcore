package top.cutexingluo.core.utils.se.algo.math;

/**
 * math 核心方法
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 12:04
 */
public class XTMathCore {

    public static double eps = 1e-10;


    /**
     * 两数相减进行比较
     *
     * @return 小于-1，等于0，大于1
     */
    public static int sgn(double value) {
        return value < -eps ? -1 : (value > eps ? 1 : 0);
    }

    /**
     * 获取 数值运算的 double  上界
     *
     * @param eps 精度值
     * @since 1.1.1
     */
    public static double getUpValue(double value, double eps) {
        return value > Double.MAX_VALUE - eps ? Double.MAX_VALUE : value + eps;
    }

    /**
     * 获取 数值运算的 double  下界
     *
     * @param eps 精度值
     * @since 1.1.1
     */
    public static double getDownValue(double value, double eps) {
        return value < -Double.MAX_VALUE + eps ? -Double.MAX_VALUE : value - eps;
    }

    /**
     * 获取 数值运算的 float  上界
     *
     * @param eps 精度值
     * @since 1.1.2
     */
    public static float getUpValue(float value, float eps) {
        return value > Double.MAX_VALUE - eps ? Float.MAX_VALUE : value + eps;
    }

    /**
     * 获取 数值运算的 float  下界
     *
     * @param eps 精度值
     * @since 1.1.2
     */
    public static float getDownValue(float value, float eps) {
        return value < -Float.MAX_VALUE + eps ? -Float.MAX_VALUE : value - eps;
    }
}
