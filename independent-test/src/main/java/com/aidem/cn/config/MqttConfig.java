package com.aidem.cn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author aidem
 * @date 2020-12-17
 * @description mqtt配置类
 */
@Component
@ConfigurationProperties(prefix = "mqtt")
@Data
public class MqttConfig {

    private String host;
    private String uploadTopic;
    private String issuedTopicPre;
    private String subChientId;
    private String pubChientId;
    private Boolean cleanSession;
    private Integer qosOne;
    private Boolean retained;
    private Integer connectionTimeout;
    private Integer keepAliveInterval;

}
