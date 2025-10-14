package top.cutexingluo.core.basepackage.action.plugin;

import top.cutexingluo.core.basepackage.action.config.IConfig;

/**
 * control plugin, 控制插件
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 11:04
 */
public interface IControlPlugin<T,C extends IConfig,H extends IPluginHandler<T,C>> extends IPlugin {


    /**
     * Process objects through configuration and action
     * <p>通过配置和行动处理对象</p>
     *
     *
     * @param config the config
     * @param handler the handler
     * @return this
     */
    IControlPlugin<T, C,H> operate(C config,H  handler);
}
