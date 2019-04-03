package com.fexco.fmsolana.cluegame;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;

public class ActiveMQClientExample {

	private static final int DELIVERY_MODE = DeliveryMode.NON_PERSISTENT;
	private static final int ACKNOWLEDGE_MODE = Session.AUTO_ACKNOWLEDGE;

	// The Endpoint, Username, Password, and Queue should be externalized and
	// configured through environment variables or dependency injection.
	private static final String ENDPOINT = "ssl://b-fcc54676-0544-4932-b2e6-94541f0465cd-1.mq.us-east-2.amazonaws.com:61617"; // "ssl://x-xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx-x.mq.us-east-1.amazonaws.com:61617"
	private static final String USERNAME = "fmsolana";
	private static final String PASSWORD = "Ya1hRdT8EF2hS0eZG9Ky";
	private static final String QUEUE = "MyQueue";

	public static void main(String[] args) throws JMSException {
		// Create a connection factory.
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ENDPOINT);

		// Specify the username and password.
		connectionFactory.setUserName(USERNAME);
		connectionFactory.setPassword(PASSWORD);

		// Create a pooled connection factory.
		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
		pooledConnectionFactory.setConnectionFactory(connectionFactory);
		pooledConnectionFactory.setMaxConnections(10);

		// Establish a connection for the producer.
		Connection producerConnection = pooledConnectionFactory.createConnection();
		producerConnection.start();

		// Create a session.
		Session producerSession = producerConnection.createSession(false, ACKNOWLEDGE_MODE);

		// Create a queue named "MyQueue".
		Destination producerDestination = producerSession.createQueue(QUEUE);

		// Create a producer from the session to the queue.
		MessageProducer producer = producerSession.createProducer(producerDestination);
		producer.setDeliveryMode(DELIVERY_MODE);

		// Create a message.
		String text = "Hello from Amazon MQ!";
		TextMessage producerMessage = producerSession.createTextMessage(text);

		// Send the message.
		producer.send(producerMessage);
		System.out.println("Message sent.");

		// Clean up the producer.
		producer.close();
		producerSession.close();
		producerConnection.close();

		// Establish a connection for the consumer.
		// Note: Consumers should not use PooledConnectionFactory.
		Connection consumerConnection = connectionFactory.createConnection();
		consumerConnection.start();

		// Create a session.
		Session consumerSession = consumerConnection.createSession(false, ACKNOWLEDGE_MODE);

		// Create a queue named "MyQueue".
		Destination consumerDestination = consumerSession.createQueue(QUEUE);

		// Create a message consumer from the session to the queue.
		MessageConsumer consumer = consumerSession.createConsumer(consumerDestination);

		// Begin to wait for messages.
		Message consumerMessage = consumer.receive(1000);

		// Receive the message when it arrives.
		TextMessage consumerTextMessage = (TextMessage) consumerMessage;
		System.out.println("Message received: " + consumerTextMessage.getText());

		// Clean up the consumer.
		consumer.close();
		consumerSession.close();
		consumerConnection.close();
		pooledConnectionFactory.stop();
	}
}