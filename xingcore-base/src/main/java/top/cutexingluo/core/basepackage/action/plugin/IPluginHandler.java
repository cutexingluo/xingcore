package top.cutexingluo.core.basepackage.action.plugin;

import top.cutexingluo.core.basepackage.action.config.IConfig;
import top.cutexingluo.core.basepackage.action.base.ConsumerHandler;

/**
 * plugin handler
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 9:51
 * @since 1.2.0
 */
public interface IPluginHandler<T,C extends IConfig> extends ConsumerHandler<T,C> {


    @Override
    void handle(T target, C config);
}
