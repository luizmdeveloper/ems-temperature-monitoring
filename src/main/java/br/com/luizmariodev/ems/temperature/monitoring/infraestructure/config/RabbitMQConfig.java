package br.com.luizmariodev.ems.temperature.monitoring.infraestructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${queue.temperature.processing}")
    private String queueProcessing;

    @Value("${exchange.temperature.received}")
    private String exchangeReceived;

    @Value("${queue.temperature.alerting}")
    private String queueAlerting;

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue queueProcessing() {
        return QueueBuilder.durable(queueProcessing).build();
    }

    @Bean
    public Queue queueAlerting() {
        return QueueBuilder.durable(queueAlerting).build();
    }

    public FanoutExchange exchange() {
        return ExchangeBuilder.fanoutExchange(exchangeReceived).build();
    }

    @Bean
    public Binding bindingProcessing() {
        return BindingBuilder.bind(queueProcessing()).to(exchange());
    }

    @Bean
    public Binding bindingAlerting() {
        return BindingBuilder.bind(queueAlerting()).to(exchange());
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
