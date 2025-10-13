package top.cutexingluo.core.basepackage.function;

import java.util.Objects;

/**
 * 四元消费接口
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/11 16:50
 * @since 1.2.0
 */
@FunctionalInterface
public interface TetraConsumer<T, U, V,W> {

    void accept(T t, U u, V v,W w);

    default TetraConsumer<T, U, V,W> andThen(TetraConsumer<? super T, ? super U, ? super V,? super W> after) {
        Objects.requireNonNull(after);
        return (T t, U u, V v,W w) -> {
            accept(t, u, v,w);
            after.accept(t, u, v,w);
        };
    }
}
