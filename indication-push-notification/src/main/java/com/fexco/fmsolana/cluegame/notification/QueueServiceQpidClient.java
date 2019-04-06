package com.fexco.fmsolana.cluegame.notification;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueServiceQpidClient {

	private IDeliverAgent deliverAgent;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String QUEUE_NAME = "CLUE_GAME_QUEUE";

	public boolean sendMessage(String stringmessage, int timeDelay) {
		try (InputStream resourceAsStream = this.getClass().getResourceAsStream("/queue.properties")) {
			Properties properties = new Properties();
			properties.load(resourceAsStream);
			Context context = new InitialContext(properties);

			ConnectionFactory factory = (ConnectionFactory) context.lookup("qpidConnectionfactory");
			Destination queue = (Destination) context.lookup("topicExchange");

			Connection connection = factory.createConnection("fmsolana", "Ya1hRdT8EF2hS0eZG9Ky");
			// connection.setExceptionListener(new MyExceptionListener());
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageProducer messageProducer = session.createProducer(queue);
			MessageConsumer messageConsumer = session.createConsumer(queue);

			TextMessage message = session.createTextMessage("Hello world!");
			messageProducer.send(message, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY,
					Message.DEFAULT_TIME_TO_LIVE);
			TextMessage receivedMessage = (TextMessage) messageConsumer.receive(100000L);

			if (receivedMessage != null) {
				System.out.println(receivedMessage.getText());
			} else {
				System.out.println("No message received within the given timeout!");
			}

			connection.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return false;
	}

	public boolean sendMessage2(String message, int timeDelay)
			throws IOException, TimeoutException, NoSuchAlgorithmException {

		try (InputStream resourceAsStream = this.getClass().getResourceAsStream("/queue.properties")) {
			Properties properties = new Properties();
			properties.load(resourceAsStream);
			Context context = new InitialContext(properties);

			ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("qpidConnectionfactory");
			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = (Destination) context.lookup("topicExchange");

			MessageProducer messageProducer = session.createProducer(destination);
			MessageConsumer messageConsumer = session.createConsumer(destination);

			TextMessage textMessage = session.createTextMessage("Hello world!");
			messageProducer.send(textMessage);

			textMessage = (TextMessage) messageConsumer.receive();
			System.out.println(textMessage.getText());

			connection.close();
			context.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return true;

	}

//	public boolean recvMessage() throws IOException, TimeoutException, NoSuchAlgorithmException {
//		ConnectionFactory factory = getFactory();
//
//		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
//			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//
//			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//				String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
//				deliverAgent.pushMessage(message);
//			};
//			channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
//			});
//		}
//		return false;
//
//	}

//	private ConnectionFactory getFactory() throws NoSuchAlgorithmException {
//		ConnectionFactory factory = new ConnectionFactory();
//		factory.useSslProtocol(SSLContext.getDefault());
//		try {
//
//			factory.setUri("stomp://b-fcc54676-0544-4932-b2e6-94541f0465cd-1.mq.us-east-2.amazonaws.com:61614");
//			// factory.setUri("amqp://b-fcc54676-0544-4932-b2e6-94541f0465cd-1.mq.us-east-2.amazonaws.com:5671");
//		} catch (KeyManagementException | NoSuchAlgorithmException | URISyntaxException e) {
//			logger.error("error in ");
//		}
//		factory.setUsername("fmsolana");
//		factory.setPassword("Ya1hRdT8EF2hS0eZG9Ky");
//		return factory;
//	}

}
