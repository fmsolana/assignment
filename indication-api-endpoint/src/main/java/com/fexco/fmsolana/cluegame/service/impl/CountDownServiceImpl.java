package com.fexco.fmsolana.cluegame.service.impl;

import javax.jms.JMSException;

import com.fexco.fmsolana.cluegame.bean.game.UserCheckTime;
import com.fexco.fmsolana.cluegame.notification.QueueService;
import com.fexco.fmsolana.cluegame.notification.QueueServiceActiveMQImpl;
import com.fexco.fmsolana.cluegame.service.CountDownService;
import com.google.gson.Gson;

public class CountDownServiceImpl implements CountDownService {

	private QueueService queueService = new QueueServiceActiveMQImpl();

	private Gson gson = new Gson();

	public CountDownServiceImpl() {
		super();
	}

	public CountDownServiceImpl(QueueService queueService) {
		super();
		this.queueService = queueService;
	}

	@Override
	public boolean pushNotification(UserCheckTime userCheckTime) {
		try {
			queueService.sendMessage(gson.toJson(userCheckTime), userCheckTime.getUserId());
		} catch (JMSException e) {
			return false;
		}
		return true;
	}

	@Override
	public UserCheckTime getNotification(String userId) {
		String json;
		try {
			json = queueService.receiveMessage(userId);
		} catch (JMSException e) {
			return null;
		}
		return gson.fromJson(json, UserCheckTime.class);
	}

}
