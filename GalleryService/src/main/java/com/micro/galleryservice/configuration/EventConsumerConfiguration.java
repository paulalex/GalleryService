package com.micro.galleryservice.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.micro.galleryservice.consumers.EventConsumer;


@Configuration
public class EventConsumerConfiguration
{

	@Bean
	public Exchange eventExchange()
	{
		return new TopicExchange("eventExchange");
	}


	@Bean
	public Queue queue()
	{
		return new Queue("imageServiceQueue");
	}


	@Bean
	public Binding binding(final Queue queue, final Exchange eventExchange)
	{
		return BindingBuilder
				.bind(queue)
				.to(eventExchange)
				.with("image.*")
				.noargs();
	}


	@Bean
	public EventConsumer eventReceiver()
	{
		return new EventConsumer();
	}
}

