package top.cutexingluo.core.basepackage.action.base;

/**
 * handler interface
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 9:48
 */
public interface IHandler <T,C,E>{
    /**
     * handle
     *
     * @param target the target
     * @param config the config
     * @return the result
     */
    E handle(T target, C config);
}
