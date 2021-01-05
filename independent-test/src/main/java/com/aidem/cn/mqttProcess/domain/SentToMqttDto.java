package com.aidem.cn.mqttProcess.domain;

import lombok.Data;

/**
 * @author aidem
 * @date 2020-12-17
 * @description 推送消息到mqtt的dto
 */
@Data
public class SentToMqttDto {

    /**
     * 消息内容
     */
    private String payLoad;

    /**
     * topic名称
     */
    private String topic;

}
