package com.fexco.fmsolana.cluegame.server.operation;

import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.repository.GameRepository;
import com.fexco.fmsolana.cluegame.server.exceptions.GameRequestException;

public class GameRequest {

	public Game getGameById(String gameId) throws GameRequestException {
		validateGameId(gameId);
		return GameRepository.getGame(gameId);
	}

	private void validateGameId(String gameId) throws GameRequestException {
		if(gameId==null || gameId.contains(" ") || gameId.length()>10)
		throw new GameRequestException("not id Allow "+gameId);
	}

}
