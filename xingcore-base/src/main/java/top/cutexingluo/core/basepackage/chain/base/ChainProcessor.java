package top.cutexingluo.core.basepackage.chain.base;


import top.cutexingluo.core.basepackage.checker.ValueChecker;

/**
 * 链基础方法
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2024/9/10 15:50
 * @since 1.1.4
 */
public interface ChainProcessor extends ValueChecker {


    /**
     * If a value is present, returns true, otherwise false.
     * <p>目标是否不为空</p>
     */
    boolean isPresent();

    /**
     * If a value is not present, returns true, otherwise false.
     * <p>目标是否为空</p>
     */
    boolean isEmpty();
}
