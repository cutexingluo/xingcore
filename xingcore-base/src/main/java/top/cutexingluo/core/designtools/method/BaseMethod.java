package top.cutexingluo.core.designtools.method;

import java.lang.reflect.InvocationTargetException;

/**
 * Method 反射接口
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2023/2/2 18:41
 */
public interface BaseMethod {
    /**
     * 调用方法
     */
    <T> Object invoke(T item, Object... values) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

}
