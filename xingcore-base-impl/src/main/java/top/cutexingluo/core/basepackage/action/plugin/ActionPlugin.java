package top.cutexingluo.core.basepackage.action.plugin;

import top.cutexingluo.core.basepackage.action.config.IConfig;

/**
 * action plugin, 行动插件
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 10:37
 */
public class ActionPlugin<T,C extends IConfig,H extends IPluginHandler<T,C>>
        implements IActionPlugin<T,C,H>{

    /**
     *  config, 配置
     */
    protected C config;

    public ActionPlugin(C config) {
        this.config = config;
    }

    @Override
    public ActionPlugin<T, C, H> apply(T target, H handler) {
        handler.handle(target, config);
        return this;
    }
}
