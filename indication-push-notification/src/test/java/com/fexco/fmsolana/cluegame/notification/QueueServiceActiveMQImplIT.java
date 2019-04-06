package com.fexco.fmsolana.cluegame.notification;

import static org.junit.Assert.assertEquals;

import javax.jms.JMSException;

import org.junit.Test;

public class QueueServiceActiveMQImplIT {

	private QueueService queueService = new QueueServiceActiveMQImpl();

	@Test
	public void sendMessageAndRecive() throws JMSException {
		queueService.sendMessage("Hola Mundo", "userId_test");

		String message = queueService.receiveMessage("userId_test");
		assertEquals("Hola Mundo", message);
	}

}
