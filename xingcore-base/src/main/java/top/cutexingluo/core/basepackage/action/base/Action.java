package top.cutexingluo.core.basepackage.action.base;

/**
 * action, 基础  action
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 9:36
 */

@FunctionalInterface
public interface Action<T,C,E,H extends IHandler<T, C, E>> extends IAction<T, C, E, H , Action<T, C, E,H>> {


    @Override
    Action<T, C, E,H> pack(T target, C config, H handler);
}
