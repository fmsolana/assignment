package com.fexco.fmsolana.cluegame.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.bean.game.GameUser;
import com.fexco.fmsolana.cluegame.bean.game.UserCheckTime;
import com.fexco.fmsolana.cluegame.server.exceptions.GameRequestException;

public class UserRepositoryTest {

	@Test
	public void getUser() {
		GameUser user = UserRepository.getUser("user2342");
		assertNotNull(user);
		assertEquals("user2342", user.getUserId());
	}

	@Test
	public void testStartGameAndCheckTime() throws GameRequestException, InterruptedException {
		UserRepository.startGame(new Game("gameId", null, "giftId"), "userId");
		UserCheckTime userCheckTime = UserRepository.getUserCheckTime("gameId", "userId");
		assertTrue(userCheckTime.getInitTime() > 0);
		Thread.sleep(2);
		assertTrue("must have passed the time", System.currentTimeMillis() - userCheckTime.getInitTime() > 0);

	}
}
