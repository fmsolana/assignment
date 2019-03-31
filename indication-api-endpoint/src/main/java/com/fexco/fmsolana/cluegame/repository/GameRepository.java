package com.fexco.fmsolana.cluegame.repository;

import java.util.Arrays;

import com.fexco.fmsolana.cluegame.bean.game.Clue;
import com.fexco.fmsolana.cluegame.bean.game.Game;

public class GameRepository {

	public static Game getGame(String gameId) {
		return new Game(gameId, Arrays.asList(new Clue(1), new Clue(2), new Clue(3)));
	}

	public static Clue starGame(String gameId, String userId) {
		Game game = getGame(gameId);
		UserRepository.startGame(game, userId);
		return game.getClue(0);
	}

}
