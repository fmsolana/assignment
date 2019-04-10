package com.fexco.fmsolana.cluegame.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.bean.game.GameUser;
import com.fexco.fmsolana.cluegame.bean.game.UserCheckTime;
import com.fexco.fmsolana.cluegame.server.exceptions.GameRequestException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTest {

	@Test
	public void getUser() {
		GameUser user = UserRepository.getUser("user2342");
		assertNotNull(user);
		assertEquals("user2342", user.getUserId());
	}

	@Test
	public void testaStartGameAndCheckTime() throws GameRequestException, InterruptedException {
		UserRepository.startGame(new Game("gameId", null, "giftId"), "userId");
		UserCheckTime userCheckTime = UserRepository.getUserCheckTime("gameId", "userId");
		assertTrue(userCheckTime.getInitTime() > 0);
		Thread.sleep(2);
		assertTrue("must have passed the time", System.currentTimeMillis() - userCheckTime.getInitTime() > 0);
	}

	@Test
	public void testaStartGameNouserPlaying() throws GameRequestException, InterruptedException {
		UserCheckTime userCheckTime = UserRepository.getUserCheckTime("gameId", "userNoPlaying");
		assertNull(userCheckTime);
	}

	@Test(expected = GameRequestException.class)
	public void testbTooManyUser() throws GameRequestException {
		for (int i = 0; i < 10001; i++) {
			UserRepository.startGame(new Game("gameId", null, "giftId"), "gameid" + i);
		}
	}

	@Test
	public void testcResetPlayers() {
		UserRepository.resetPlayers();
	}

}
