package top.cutexingluo.test;

import top.cutexingluo.core.basepackage.action.config.Config;
import top.cutexingluo.core.basepackage.action.plugin.ControlPlugin;
import top.cutexingluo.core.basepackage.action.plugin.IPluginHandler;

/**
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 10:06
 */
public class Test {

    public static void main(String[] args) {

        Config config = new Config(null);


        ControlPlugin<String, Config, IPluginHandler<String, Config>> test = new ControlPlugin<>(
                "test"
        );

        test.operate(config,(s,c)->{
            System.out.println(s);
        });

    }
}
