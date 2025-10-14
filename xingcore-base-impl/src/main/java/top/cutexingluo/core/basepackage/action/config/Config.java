package top.cutexingluo.core.basepackage.action.config;

import lombok.Data;

import java.util.Map;

/**
 * config, 配置实现类
 *
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/14 10:08
 */
@Data
public class Config implements IConfig {

    private Map<String, Object> meta;

    public Config(Map<String, Object> meta) {
        this.meta = meta;
    }


    @Override
    public Map<String, Object> getMeta() {
        return meta;
    }
}
