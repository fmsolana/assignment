package com.fexco.fmsolana.cluegame.repository;

import com.fexco.fmsolana.cluegame.bean.game.Clue;
import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.repository.dao.GameDao;
import com.fexco.fmsolana.cluegame.repository.dao.impl.MockGameDaoImpl;
import com.fexco.fmsolana.cluegame.repository.entity.GameVO;
import com.fexco.fmsolana.cluegame.repository.translate.GameVOToGameTranslate;

public class GameRepository {

	private GameRepository() {

	}

	private static GameDao gameDao = new MockGameDaoImpl();

	private static GameVOToGameTranslate translate = new GameVOToGameTranslate();

	public static Game getGame(String gameId) {
		GameVO gameVo = gameDao.getGame(gameId);
		return translate.transformToGame(gameVo);
	}

	public static Clue starGame(String gameId, String userId) {
		Game game = getGame(gameId);
		if (game == null)
			return null;
		UserRepository.startGame(game, userId);
		return game.getClue(1);
	}

}
