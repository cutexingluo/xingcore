package top.cutexingluo.core.basepackage.action.plugin;

import top.cutexingluo.core.basepackage.action.config.IConfig;

/**
 * control plugin, 控制插件
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 11:07
 */
public class ControlPlugin <T,C extends IConfig,H extends IPluginHandler<T,C>>
        implements IControlPlugin<T,C,H>{


    /**
     *  target, 目标
     */
    protected T target;

    public ControlPlugin(T target) {
        this.target = target;
    }

    @Override
    public ControlPlugin<T, C, H> operate(C config, H handler) {
        handler.handle(target, config);
        return this;
    }
}
