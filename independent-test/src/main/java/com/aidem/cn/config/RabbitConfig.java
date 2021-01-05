package com.aidem.cn.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Rabbitmq配置类
 * Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
 * Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
 * Queue:消息的载体,每个消息都会被投到一个或多个队列。
 * Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
 * Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
 * vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
 * Producer:消息生产者,就是投递消息的程序.
 * Consumer:消息消费者,就是接受消息的程序.
 * Channel:消息通道,在客户端的每个连接里,可建立多个channel.
 *
 * @author aidem
 * @date 2020-12-18
 */

@Configuration
public class RabbitConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    // exchange - routerKey - queue
    // ================================================= 数据处理server =================================================
    public static final String DATA_PROCESS_EXCHANGE = "data-process-exchange";
    // 数据处理server 测试 key - queue
    public static final String DATA_PROCESS_KEY = "data-process-key";
    public static final String DATA_PROCESS_QUEUE = "data-process-queue";
    // om 消息key - queue
    public static final String DATA_OM_KEY = "data-om-key";
    public static final String DATA_OM_QUEUE = "data-om-queue";

    // ================================================= api server =================================================
    public static final String API_EXCHANGE = "api-exchange";
    // api server 测试 key - queue
    public static final String API_KEY = "api-key";
    public static final String API_QUEUE = "api-queue";
    // history 80 对接mqtt的消息 key - queue
    public static final String DATA_80_MQTT_KEY = "data-80-mqtt-key";
    public static final String DATA_80_MQTT_QUEUE = "data-80-mqtt-queue";
    // history 80 对接amqp的消息 key - queue
    public static final String DATA_80_AMQP_KEY = "data-80-amqp-key";
    public static final String DATA_80_AMQP_QUEUE = "data-80-amqp-queue";

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    /**
     * 创建工厂
     */
    @Bean
    public ConnectionFactory defaultConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /**
	 * 使用工厂信息创建操作对象
     * 必须是prototype类型
	 */
	@Bean(name = "defaultRabbitTemplate")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(defaultConnectionFactory());
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    /**
     * 交换机，开启持久化
     *  针对消费者配置
     *  1. 设置交换机类型
     *  2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange dataProcessExchange() {
        return new DirectExchange(DATA_PROCESS_EXCHANGE, true, false);
    }
    @Bean
    public DirectExchange apiExchange() {
        return new DirectExchange(API_EXCHANGE, true, false);
    }

    /**
     * 声明队列，开启持久化
     */
    @Bean
    public Queue queueDataProcess() {
        return new Queue(DATA_PROCESS_QUEUE, true);
    }
    @Bean
    public Queue queueApi() {
        return new Queue(API_QUEUE, true);
    }
    @Bean
    public Queue queueOm() {
        return new Queue(DATA_OM_QUEUE, true);
    }
    @Bean
    public Queue queue80amqp() {
        return new Queue(DATA_80_AMQP_QUEUE, true);
    }
    @Bean
    public Queue queue80mqtt() {
        return new Queue(DATA_80_MQTT_QUEUE, true);
    }

    /**
     * 将队列使用key绑定到交换机
     */
	@Bean
	public Binding bindingDataProcess() {
		return BindingBuilder.bind(queueDataProcess()).to(dataProcessExchange()).with(DATA_PROCESS_KEY);
	}
	@Bean
	public Binding bindingApiProcess() {
		return BindingBuilder.bind(queueApi()).to(apiExchange()).with(API_KEY);
	}
	@Bean
	public Binding bindingOmProcess() {
        return BindingBuilder.bind(queueOm()).to(dataProcessExchange()).with(DATA_OM_KEY);
	}
    @Bean
    public Binding binding80AmqpProcess() {
        return BindingBuilder.bind(queue80amqp()).to(dataProcessExchange()).with(DATA_80_AMQP_KEY);
    }
    @Bean
    public Binding binding80Process() {
        return BindingBuilder.bind(queue80mqtt()).to(dataProcessExchange()).with(DATA_80_MQTT_KEY);
    }

	// ================================================ 消费者设置 ================================================

    /**
     * 单一消费者
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(defaultConnectionFactory());
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        factory.setBatchSize(1);
        return factory;
    }

    /**
     * 多个消费者
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory,defaultConnectionFactory());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConcurrentConsumers(2);
        factory.setMaxConcurrentConsumers(2);
        factory.setPrefetchCount(1);
        return factory;
    }

}
