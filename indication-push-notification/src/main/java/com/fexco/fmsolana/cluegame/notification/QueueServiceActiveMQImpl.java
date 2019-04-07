package com.fexco.fmsolana.cluegame.notification;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;

/**
 * Implement {@link QueueService} with ActiveMQ
 * 
 * @author Felipe
 *
 */
public class QueueServiceActiveMQImpl implements QueueService {
	// TODO make configurable
	private static final String WIRE_LEVEL_ENDPOINT = "ssl://b-92b97b14-df16-4d7f-bd6a-76977e2e771e-1.mq.us-east-2.amazonaws.com:61617";
	private static final String ACTIVE_MQ_USERNAME = "fmsolana";
	private static final String ACTIVE_MQ_KEYWORD = "Ya1hRdT8EF2hS0eZG9Ky";
	private static final String QUEUE_NAME = "CLUE_GAME_QUEUE";

	private ActiveMQConnectionFactory connectionFactory;
	private PooledConnectionFactory pooledConnectionFactory;

	public QueueServiceActiveMQImpl() {
		connectionFactory = createActiveMQConnectionFactory();
		pooledConnectionFactory = createPooledConnectionFactory(connectionFactory);
	}

	public QueueServiceActiveMQImpl(ActiveMQConnectionFactory connectionFactory,
			PooledConnectionFactory pooledConnectionFactory) {
		this.connectionFactory = connectionFactory;
		this.pooledConnectionFactory = pooledConnectionFactory;
	}

	@Override
	public void sendMessage(String message) throws JMSException {
		sendMessage(message, null);
	}

	@Override
	public void sendMessage(String message, String userId) throws JMSException {
		Connection producerConnection = pooledConnectionFactory.createConnection();
		producerConnection.start();

		Session producerSession = producerConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		String queueName = QUEUE_NAME + userId;
		MessageProducer producer = producerSession.createProducer(producerSession.createQueue(queueName));
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		TextMessage producerMessage = producerSession.createTextMessage(message);

		// Send the message.
		producer.send(producerMessage);

		producer.close();
		producerSession.close();
		producerConnection.close();
	}

	@Override
	public String receiveMessage(String userId) throws JMSException {
		Connection consumerConnection = connectionFactory.createConnection();
		consumerConnection.start();

		Session consumerSession = consumerConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// Create a message consumer from the session to the queue.
		String queueName = QUEUE_NAME + userId;
		MessageConsumer consumer = consumerSession.createConsumer(consumerSession.createQueue(queueName));

		// Receive the message when it arrives.
		TextMessage consumerTextMessage = (TextMessage) consumer.receive(1000);

		// Clean up the consumer.
		consumer.close();
		consumerSession.close();
		consumerConnection.close();
		return consumerTextMessage.getText();
	}

	private static PooledConnectionFactory createPooledConnectionFactory(ActiveMQConnectionFactory connectionFactory) {
		// Create a pooled connection factory.
		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
		pooledConnectionFactory.setConnectionFactory(connectionFactory);
		pooledConnectionFactory.setMaxConnections(10);
		return pooledConnectionFactory;
	}

	private static ActiveMQConnectionFactory createActiveMQConnectionFactory() {
		// Create a connection factory.
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(WIRE_LEVEL_ENDPOINT);

		// Pass the username and password.
		connectionFactory.setUserName(ACTIVE_MQ_USERNAME);
		connectionFactory.setPassword(ACTIVE_MQ_KEYWORD);
		return connectionFactory;
	}

	@Override
	public String receiveMessage() throws JMSException {
		return receiveMessage(null);
	}

}
