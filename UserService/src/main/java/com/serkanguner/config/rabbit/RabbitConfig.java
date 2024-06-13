package com.serkanguner.config.rabbit;

import lombok.Builder;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    Queue queueMail(){
        return new Queue("queue.mail");
    }
    @Bean
    DirectExchange exchange(){
        return new DirectExchange("exchange.direct");
    }
    @Bean
    Binding bindingMail(Queue queueMail, DirectExchange exchange){
        return BindingBuilder
                .bind(queueMail)
                .to(exchange)
                .with("Routing.mail");
    }
    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    //5.adim Converter'i RaabitTemplate'e atama
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
