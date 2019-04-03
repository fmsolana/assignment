package com.fexco.fmsolana.cluegame;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

	private final static String QUEUE_NAME = "CLUE_GAME";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		// factory.setHost("b-a6e58ce1-8c91-49b1-a39b-3f9c1a499387-1.mq.us-east-2.amazonaws.com");
		factory.setUri("amqp://b-fcc54676-0544-4932-b2e6-94541f0465cd-1.mq.us-east-2.amazonaws.com:5671");

		// Specify the username and password.
		factory.setUsername("fmsolana");
		factory.setPassword("Ya1hRdT8EF2hS0eZG9Ky");
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			String message = "Hello World!";
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + message + "'");
		}
	}
}