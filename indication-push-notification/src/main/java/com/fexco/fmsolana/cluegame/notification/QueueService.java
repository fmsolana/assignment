package com.fexco.fmsolana.cluegame.notification;

import javax.jms.JMSException;

public interface QueueService {

	/**
	 * Send message to queue, the queue and message depends on implementation See
	 * {@link QueueServiceActiveMQImpl} for example
	 * 
	 * @param message
	 * @throws JMSException
	 */
	void sendMessage(String message) throws JMSException;

	/**
	 * * Send message to queue, the queue and message depends on implementation See
	 * {@link QueueServiceActiveMQImpl} for example
	 * 
	 * @param message
	 * @param clientId is the receiver for message
	 * @throws JMSException
	 */
	void sendMessage(String message, String clientId) throws JMSException;

	/**
	 * ReceiveMessage From queue, the queue and message depends on implementation
	 * See {@link QueueServiceActiveMQImpl} for example
	 * 
	 * @return
	 * @throws JMSException
	 */
	String receiveMessage() throws JMSException;

	/**
	 * ReceiveMessage From queue, the queue and message depends on implementation
	 * See {@link QueueServiceActiveMQImpl} for example
	 * 
	 * @param clientId id for receiver messager
	 * @return
	 * @throws JMSException
	 */
	String receiveMessage(String clientId) throws JMSException;

}
