package top.cutexingluo.core.designtools.builder;

/**
 * 建造者Builder接口
 * <p>builder interface</p>
 * <p>v1.2.0 移除 getBuilder() </p>
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2023/2/2 18:38
 */
@FunctionalInterface
public interface Builder<T> {

    /**
     * build the target
     */
    T build();
}