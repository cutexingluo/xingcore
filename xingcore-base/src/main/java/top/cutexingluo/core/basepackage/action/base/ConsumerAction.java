package top.cutexingluo.core.basepackage.action.base;

/**
 * consumer action, 消费者 action
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 10:02
 */
@FunctionalInterface
public interface ConsumerAction<T,C,H extends ConsumerHandler<T, C>> {

    /**
     * pack the action
     *
     * @param target the target
     * @param config the config
     * @param handler the handler
     * @return this
     */
    ConsumerAction<T, C,  H> pack(T target, C config, H handler);
}
