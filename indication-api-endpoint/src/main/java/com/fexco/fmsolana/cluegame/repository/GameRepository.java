package com.fexco.fmsolana.cluegame.repository;

import com.fexco.fmsolana.cluegame.bean.game.Game;

public class GameRepository {

	public static Game getGame(String gameId) {
		return new Game(gameId);
	}

}
