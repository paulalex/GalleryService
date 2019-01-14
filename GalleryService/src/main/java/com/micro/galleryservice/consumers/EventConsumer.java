package com.micro.galleryservice.consumers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


public class EventConsumer
{

	private final Logger logger = LoggerFactory.getLogger(EventConsumer.class);


	@RabbitListener(queues = "imageServiceQueue")
	public void receive(final String message)
	{
		this.logger.info("Received message '{}'", message);
	}
}

