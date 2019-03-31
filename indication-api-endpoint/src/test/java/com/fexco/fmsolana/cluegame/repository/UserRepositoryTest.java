package com.fexco.fmsolana.cluegame.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.fexco.fmsolana.cluegame.bean.game.GameUser;

public class UserRepositoryTest {

	@Test
	public void getUser() {
		GameUser user = UserRepository.getUser("user2342");
		assertNotNull(user);
		assertEquals("user2342", user.getUserId());
	}
}
