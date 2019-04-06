package com.fexco.fmsolana.cluegame.notification;

public interface IDeliverAgent {

	default void pushMessage(String message) {
		System.out.println(" call back message:" + message);
	};

}
