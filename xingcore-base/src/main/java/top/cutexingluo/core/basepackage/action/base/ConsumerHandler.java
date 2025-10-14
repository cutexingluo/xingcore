package top.cutexingluo.core.basepackage.action.base;

/**
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 9:51
 */
@FunctionalInterface
public interface ConsumerHandler<T,C>  {

    void handle(T target, C config);
}
