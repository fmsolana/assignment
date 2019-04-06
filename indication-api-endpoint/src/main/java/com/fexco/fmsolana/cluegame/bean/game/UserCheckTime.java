package com.fexco.fmsolana.cluegame.bean.game;

public class UserCheckTime {

	private long timeInit;
	private String userId;

	public UserCheckTime(long timeInit, String userId) {
		super();
		this.timeInit = timeInit;
		this.userId = userId;
	}

	public long getInitTime() {
		return timeInit;
	}

	public String getUserId() {
		return userId;
	}
}
