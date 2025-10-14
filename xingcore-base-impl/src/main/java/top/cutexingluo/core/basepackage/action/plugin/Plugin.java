package top.cutexingluo.core.basepackage.action.plugin;

import org.jetbrains.annotations.NotNull;
import top.cutexingluo.core.basepackage.action.config.IConfig;

/**
 * plugin, 插件
 *
 * <P>The basic behavior of the plugin can inherit this class</P>
 * <p>插件基本行为，可以继承该类</p>
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 10:29
 */
public class Plugin <T,C extends IConfig,H extends IPluginHandler<T,C>> implements IPlugin,IAllPlugin<T,C,H>{
    @Override
    public Plugin<T, C, H> pack(T target, C config, @NotNull H handler) {
        handler.handle(target, config);
        return this;
    }
}
