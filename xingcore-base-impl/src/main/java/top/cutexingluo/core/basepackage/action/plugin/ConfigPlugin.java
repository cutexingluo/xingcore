package top.cutexingluo.core.basepackage.action.plugin;

import top.cutexingluo.core.basepackage.action.config.IConfig;

/**
 * config plugin, 配置插件
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 10:27
 */
public class ConfigPlugin<T,C extends IConfig,H extends IPluginHandler<T,C>>
       implements IConfigPlugin<T,C>{

    /**
     *  handler, 处理器
     */
    protected H handler;

    public ConfigPlugin(H handler) {
        this.handler = handler;
    }

    @Override
    public ConfigPlugin<T, C,H> action(T target, C config) {
        handler.handle(target, config);
        return this;
    }
}
