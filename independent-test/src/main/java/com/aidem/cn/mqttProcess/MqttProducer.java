package com.aidem.cn.mqttProcess;

import com.aidem.cn.config.MqttConfig;
import com.aidem.cn.config.MqttProduceCallbackConfig;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aidem
 * @date 2020-12-17
 * @description 下发数据入口，经本网关转发到mqtt
 */
@RestController
@RequestMapping("/hwatch/gateway/produce")
public class MqttProducer {

    @Autowired
    private MqttConfig mqttConfig;
    private MqttClient client;
    private MqttTopic topic;

    // ========================================= private ===========================================

    /**
     * 创建MQTT操作客户端
     */
    private void OpenMqttServer(String topicUrl) {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        try {
            client = new MqttClient(mqttConfig.getHost(), mqttConfig.getPubChientId(), new MemoryPersistence());
        } catch (MqttException e) {
            e.printStackTrace();
        }
        connect(topicUrl);
    }

    /**
     * 连接服务器
     */
    private void connect(String topicUrl) {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(mqttConfig.getCleanSession());
        // 设置超时时间
        options.setConnectionTimeout(mqttConfig.getConnectionTimeout());
        // 设置会话心跳时间
        options.setKeepAliveInterval(mqttConfig.getKeepAliveInterval());
        try {
            client.setCallback(new MqttProduceCallbackConfig());
            client.connect(options);
            topic = client.getTopic(topicUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布消息
     * msg msgBytes按需传入
     */
    public void publish(String topicUrl, String msg, byte[] msgBytes) {
        // 连接mqtt
        OpenMqttServer(topicUrl);
        // 构造消息
        MqttMessage message = new MqttMessage();
        message.setQos(mqttConfig.getQosOne());
        message.setRetained(mqttConfig.getRetained());
        if (msg == null){
            message.setPayload(msgBytes);
        } else {
            message.setPayload(msg.getBytes());
        }
        // 发布
        MqttDeliveryToken token = null;
        try {
            token = topic.publish(message);
        } catch (MqttException e) {
            System.out.println("发布消息出错，具体如下:" + e);
        }
        try {
            if (token != null) {
                token.waitForCompletion();
            }
        } catch (MqttException e) {
            System.out.println("发布消息等待完成出错，具体如下:" + e);
        }
        System.out.println("message is published completely! "
                + token.isComplete());
    }

}
