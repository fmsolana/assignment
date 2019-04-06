package com.fexco.fmsolana.cluegame.service;

import com.fexco.fmsolana.cluegame.bean.game.UserCheckTime;

public interface CountDownService {

	boolean pushNotification(UserCheckTime userCheckTime);

	UserCheckTime getNotification(String userId);

}
