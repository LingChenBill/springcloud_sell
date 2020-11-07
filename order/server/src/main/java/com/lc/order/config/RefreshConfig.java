package com.lc.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 利用RefreshScope获取git配置中的字段信息(动态刷新).
 * @description:
 *    @ConfigurationProperties: 对应yml中开始字段.
 * @author: lingchen
 * @date: 2020/11/7
 */
@Data
@Component
@ConfigurationProperties("refresh")
@RefreshScope
public class RefreshConfig {

    private String name;

    private Integer age;
}
