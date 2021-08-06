package com.aidem.cn.rabbitmqProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;


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

    //时间 出生日期计算年龄
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dates = sdf.format(date);
        String birth = "1997-06-29 11:12:51";
        int age = Integer.valueOf(dates.substring(0, 4)) - Integer.valueOf(birth.substring(0, 4));
        if (dates.compareTo(birth) < 0)
            age--;
        System.out.println("age : " + age);
    }
}
