package com.fexco.fmsolana.cluegame.server.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.fexco.fmsolana.cluegame.bean.game.Clue;
import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.server.exceptions.GameRequestException;

public class GameRequestTest {

	private GameRequest gameRequest = new GameRequest();

	@Test
	public void getGameById() throws GameRequestException {
		Game game = gameRequest.getGameById("1");
		assertNotNull(game);
		assertEquals("1", game.getGameId());
	}

	@Test(expected = GameRequestException.class)
	public void getGameByIdNotIdAllow() throws GameRequestException {
		gameRequest.getGameById("Select * from user");
	}

	@Test
	public void startGame() throws GameRequestException {
		Clue clue = gameRequest.startGame("1", "user2345");
		assertNotNull(clue);
		assertEquals(1, clue.getId());
	}
}
