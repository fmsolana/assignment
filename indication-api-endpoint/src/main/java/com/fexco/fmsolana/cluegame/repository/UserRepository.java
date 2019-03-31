package com.fexco.fmsolana.cluegame.repository;

import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.bean.game.GameUser;

public class UserRepository {

	public static GameUser getUser(String userId) {
		return new GameUser(userId);
	}

	public static void startGame(Game game, String userId) {
		// TODO Auto-generated method stub
	}

}
