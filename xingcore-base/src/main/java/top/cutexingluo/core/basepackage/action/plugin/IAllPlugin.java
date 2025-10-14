package top.cutexingluo.core.basepackage.action.plugin;

import top.cutexingluo.core.basepackage.action.base.ConsumerAction;
import top.cutexingluo.core.basepackage.action.config.IConfig;

/**
 * plugin all interface
 * <p>Here, you can configure the behavior of the plugin</p>
 * <p>在这里，你可以配置所有插件的行为</p>
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 9:42
 */
public interface IAllPlugin<T,C extends IConfig,H extends IPluginHandler<T,C>> extends IPlugin,ConsumerAction<T,C,H> {

    @Override
    IAllPlugin<T, C,H> pack(T target, C config, H handler);

}
