package com.fexco.fmsolana.cluegame.notification;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QueueServiceActiveMQImplTest {
	@Mock
	private ActiveMQConnectionFactory connectionFactory;

	@Mock
	private PooledConnectionFactory pooledConnectionFactory;
	@InjectMocks
	private QueueServiceActiveMQImpl queueServiceActiveMQImpl;

	@Test
	public void sendMessage() throws JMSException {
		doTestSendMessage("clientid");
	}

	@Test
	public void sendMessageNotClient() throws JMSException {
		doTestSendMessage(null);
	}

	@Test
	public void receiveMessage() throws JMSException {
		doTestRecvMessage("clientid");
	}

	@Test
	public void receiveMessageNotClient() throws JMSException {
		doTestRecvMessage(null);
	}

	private void doTestSendMessage(String clientid) throws JMSException {
		Connection producerConection = Mockito.mock(Connection.class);
		Session producerSession = Mockito.mock(Session.class);
		MessageProducer messageProducer = Mockito.mock(MessageProducer.class);
		when(pooledConnectionFactory.createConnection()).thenReturn(producerConection);
		when(producerConection.createSession(false, Session.AUTO_ACKNOWLEDGE)).thenReturn(producerSession);
		when(producerSession.createQueue(anyString())).thenReturn(mock(Queue.class));
		when(producerSession.createProducer(any(Queue.class))).thenReturn(messageProducer);
		if (clientid != null)
			queueServiceActiveMQImpl.sendMessage("test message", clientid);
		else
			queueServiceActiveMQImpl.sendMessage("test message");
		verify(pooledConnectionFactory).createConnection();
		verify(producerConection).start();
		verify(producerSession).createProducer(any(Queue.class));
		verify(messageProducer).close();
		verify(producerSession).close();
		verify(producerConection).close();
	}

	private void doTestRecvMessage(String clientid) throws JMSException {
		Connection consumerConnection = mock(Connection.class);
		Session consumerSession = mock(Session.class);
		MessageConsumer messageConsumer = mock(MessageConsumer.class);
		when(connectionFactory.createConnection()).thenReturn(consumerConnection);
		when(consumerConnection.createSession(false, Session.AUTO_ACKNOWLEDGE)).thenReturn(consumerSession);
		when(consumerSession.createQueue(anyString())).thenReturn(mock(Queue.class));
		when(consumerSession.createConsumer(any(Queue.class))).thenReturn(messageConsumer);
		TextMessage textConsumer = mock(TextMessage.class);
		when(messageConsumer.receive(anyLong())).thenReturn(textConsumer);
		when(textConsumer.getText()).thenReturn("Hello world");
		String message;
		if (clientid != null)
			message = queueServiceActiveMQImpl.receiveMessage(clientid);
		else
			message = queueServiceActiveMQImpl.receiveMessage();
		assertEquals("Hello world", message);
		verify(connectionFactory).createConnection();
		verify(consumerConnection).start();
		verify(consumerSession).createConsumer(any(Queue.class));
		verify(messageConsumer).close();
		verify(consumerSession).close();
		verify(consumerConnection).close();
	}

}
