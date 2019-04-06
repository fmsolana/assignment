package com.fexco.fmsolana.cluegame.service;

import com.fexco.fmsolana.cluegame.bean.game.UserCheckTime;

public interface UserNotifierService {

	boolean pushNotification(UserCheckTime userCheckTime);

}
