package com.fexco.fmsolana.cluegame.repository;

import java.util.Arrays;

import com.fexco.fmsolana.cluegame.bean.game.Clue;
import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.bean.game.cluetype.CorrectPlaceClue;
import com.fexco.fmsolana.cluegame.bean.game.cluetype.OptionsClue;
import com.fexco.fmsolana.cluegame.bean.game.cluetype.WordClue;

public class GameRepository {

	public static Game getGame(String gameId) {
		return new Game(gameId, Arrays.asList(new OptionsClue(1, "option1", "option2", "correctOption"),
				new WordClue(2, "answer"), new CorrectPlaceClue(3)), "giftId");
	}

	public static Clue starGame(String gameId, String userId) {
		Game game = getGame(gameId);
		UserRepository.startGame(game, userId);
		return game.getClue(1);
	}

}
