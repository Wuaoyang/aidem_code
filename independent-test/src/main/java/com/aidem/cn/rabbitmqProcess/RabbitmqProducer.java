package com.aidem.cn.rabbitmqProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * 生产者
 *
 * @author aidem
 * @date 2020-12-18
 */
@Component
public class RabbitmqProducer implements RabbitTemplate.ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "defaultRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    /**
     * 指定 ConfirmCallback
     */
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
    }

    public void sendMsg(String exchange, String key, Object content) {
        CorrelationData correlationId = new CorrelationData();
        rabbitTemplate.convertAndSend(exchange, key, content, correlationId);
    }

    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("回调id:" + correlationData);
        if (ack) {
            logger.info("消息成功发送");
        } else {
            logger.info("消息发送失败");
        }
    }
}
