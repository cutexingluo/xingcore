package top.cutexingluo.core.basepackage.action.base;

/**
 * IAction, the base interface for all actions.
 *
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 9:30
 * @since  1.2.0
 */
@FunctionalInterface
public interface IAction <T,C,E,H extends IHandler<T,C,E>, R>{

    /**
     * decorate/pack the action
     *
     * @param target the target
     * @param config the config
     * @param handler the handler
     * @return the result
     */
    R pack(T target, C config, H handler );

}
