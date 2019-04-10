package com.fexco.fmsolana.cluegame.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.bean.game.GameUser;
import com.fexco.fmsolana.cluegame.bean.game.UserCheckTime;
import com.fexco.fmsolana.cluegame.server.exceptions.GameRequestException;

public class UserRepository {

	private UserRepository() {

	}

	private static Map<String, Game> usersPlaying = new ConcurrentHashMap<>();
	private static Map<String, UserCheckTime> usersTimePlaying = new ConcurrentHashMap<>();

	private static long maxUserPlaying = 10000;

	public static GameUser getUser(String userId) {
		return new GameUser(userId);
	}

	public static void startGame(Game game, String userId) throws GameRequestException {
		if (usersPlaying.size() < maxUserPlaying) {
			usersPlaying.put(userId, game);
			usersTimePlaying.put(userId, new UserCheckTime(System.currentTimeMillis(), userId));
		} else {
			throw new GameRequestException("Too many playing");
		}

	}

	public static UserCheckTime getUserCheckTime(String gameId, String userId) {
		Game game = usersPlaying.get(userId);
		if (game != null && game.getGameId().equals(gameId))
			return usersTimePlaying.get(userId);
		else
			return null;
	}

	public static void resetPlayers() {
		usersPlaying.clear();
		usersTimePlaying.clear();
	}

}
