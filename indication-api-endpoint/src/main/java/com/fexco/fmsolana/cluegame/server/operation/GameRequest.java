package com.fexco.fmsolana.cluegame.server.operation;

import com.fexco.fmsolana.cluegame.bean.game.Clue;
import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.repository.GameRepository;
import com.fexco.fmsolana.cluegame.server.exceptions.GameRequestException;
import com.fexco.fmsolana.cluegame.server.operation.validation.ParamValidation;

public class GameRequest {

	private ParamValidation validation = new ParamValidation();

	public Game getGameById(String gameId) throws GameRequestException {
		validation.validateParam(gameId);
		return GameRepository.getGame(gameId);
	}

	public Clue startGame(String gameId, String userId) throws GameRequestException {
		validation.validateParam(gameId);
		validation.validateParam(userId);
		return GameRepository.starGame(gameId, userId);
	}

}
