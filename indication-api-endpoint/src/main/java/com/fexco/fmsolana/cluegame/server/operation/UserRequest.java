package com.fexco.fmsolana.cluegame.server.operation;

import com.fexco.fmsolana.cluegame.bean.game.UserCheckTime;
import com.fexco.fmsolana.cluegame.repository.UserRepository;
import com.fexco.fmsolana.cluegame.server.exceptions.GameRequestException;
import com.fexco.fmsolana.cluegame.server.operation.validation.ParamValidation;

public class UserRequest {

	private ParamValidation validation = new ParamValidation();

	public UserCheckTime userCheckTime(String gameId, String userId) throws GameRequestException {
		validation.validateParam(gameId);
		validation.validateParam(userId);
		return UserRepository.getUserCheckTime(gameId, userId);
	}

}
