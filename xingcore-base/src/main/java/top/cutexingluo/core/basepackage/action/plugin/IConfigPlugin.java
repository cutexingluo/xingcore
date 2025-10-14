package top.cutexingluo.core.basepackage.action.plugin;

import top.cutexingluo.core.basepackage.action.config.IConfig;

/**
 * config plugin, 配置插件
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 10:12
 */
public interface IConfigPlugin<T,C extends IConfig>extends IPlugin{


    /**
     * Perform action operations through configuration
     * <p>通过配置执行行动操作</p>
     *
     * @param target the target
     * @param config the config
     * @return this
     */
    IConfigPlugin<T, C> action(T target,C config);

}
