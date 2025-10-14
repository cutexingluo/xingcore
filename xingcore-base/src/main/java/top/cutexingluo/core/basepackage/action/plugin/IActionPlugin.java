package top.cutexingluo.core.basepackage.action.plugin;

import top.cutexingluo.core.basepackage.action.config.IConfig;

/**
 *action plugin,  动作插件
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 10:19
 */
public interface IActionPlugin<T,C extends IConfig,H extends IPluginHandler<T,C>> extends IPlugin{


    /**
     * Operate through action processing configuration
     * <p>通过行动处理配置的操作</p>
     *
     * @param target the target
     * @param handler the handler
     * @return this
     */
    IActionPlugin<T, C,H> apply(T target,H  handler);
}
