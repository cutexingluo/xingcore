package top.cutexingluo.core.designtools.method;

import cn.hutool.core.bean.BeanUtil;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * 简易的类生成器
 * <p>对象生产器/转化器</p>
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2023/10/24 20:27
 * @since 1.0.3
 */
public class ClassMakerMore<T> extends ClassMaker<T> {


    public ClassMakerMore(@NotNull Class<T> clazz) {
        super(clazz);
    }

    public ClassMakerMore(@NotNull Class<T> clazz, boolean printTrace, Consumer<Exception> exceptionHandler) {
        super(clazz, printTrace, exceptionHandler);
    }



    /**
     * 转化为目标类
     * <p>会先判定是否是超类，如果可以转化则直接转化，否则使用 BeanUtil 进行转化</p>
     *
     * @param obj   对象
     * @param clazz 类型
     * @return 转化后的对象
     * @since 1.0.3
     */
    public static <T, O> T castTarClass(O obj, Class<T> clazz) {
        if (obj == null) return null;
        if (clazz.isAssignableFrom(obj.getClass())) return (T) obj;
        return BeanUtil.copyProperties(obj, clazz);
    }

    /**
     * 转化为子类
     * <p>会先判定是否是同一类型，如果可以转化则直接转化，否则使用 BeanUtil 进行转化</p>
     *
     * @param obj   对象
     * @param clazz 类型
     * @return 转化后的对象，抛出异常则返回 null
     * @since 1.0.3
     */
    public static <O, T extends O> T castSubclass(O obj, Class<T> clazz) {
        if (obj == null) return null;
        if (clazz == obj.getClass()) return (T) obj;
        return BeanUtil.copyProperties(obj, clazz);
    }

    /**
     * 转化为目标类
     * <p>会先判定是否是超类，如果可以转化则直接转化，否则使用 BeanUtil 进行转化</p>
     *
     * @param obj 对象
     * @return 转化后的对象
     * @since 1.0.3
     */
    public <O> T castTarClass(O obj) {
        return castTarClass(obj, clazz);
    }

    /**
     * 转化为子类
     * <p>会先判定是否是同一类型，如果可以转化则直接转化，否则使用 BeanUtil 进行转化</p>
     *
     * @param obj 对象
     * @return 转化后的对象，抛出异常则返回 null
     * @since 1.0.3
     */
    public <O> T castSubclass(O obj) {
        if (clazz.isAssignableFrom(obj.getClass())) return (T) obj;
        return castSubclass(obj, clazz);
    }



}
