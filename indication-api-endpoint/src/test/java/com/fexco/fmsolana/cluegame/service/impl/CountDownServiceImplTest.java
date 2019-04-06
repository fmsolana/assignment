package com.fexco.fmsolana.cluegame.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jms.JMSException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fexco.fmsolana.cluegame.bean.game.UserCheckTime;
import com.fexco.fmsolana.cluegame.notification.QueueService;
import com.google.gson.Gson;

@RunWith(MockitoJUnitRunner.class)
public class CountDownServiceImplTest {
	@Mock
	private QueueService queueService;
	@InjectMocks
	private CountDownServiceImpl countDownServiceImpl;

	@Test
	public void pushNotification() {

		assertTrue(countDownServiceImpl.pushNotification(new UserCheckTime(1, "userId")));
	}

	@Test
	public void getNotification() throws JMSException {
		String userId = "userId";
		when(queueService.receiveMessage(userId)).thenReturn((new Gson()).toJson(new UserCheckTime(1, "userId")));
		assertNotNull(countDownServiceImpl.getNotification(userId));
		verify(queueService).receiveMessage(userId);
	}
}
