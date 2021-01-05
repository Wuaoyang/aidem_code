package com.aidem.cn.mqttProcess.domain;

import lombok.Data;

/**
 * @author aidem
 * @date 2020-12-30
 * @description DATA_80_MQTT_QUEUE 消息接收dto
 */
@Data
public class MqttDeviceHisRequest {

    private String deviceId;
    private byte[] payLoad;

}
