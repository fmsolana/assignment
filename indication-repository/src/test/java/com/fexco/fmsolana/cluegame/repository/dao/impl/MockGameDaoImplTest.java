package com.fexco.fmsolana.cluegame.repository.dao.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class MockGameDaoImplTest {

	@Test
	public void testLoad() {
		MockGameDaoImpl mock = new MockGameDaoImpl();
		assertNotNull(mock.getGame("gameId"));
		assertNotNull(mock.getGame("1"));
		assertNotNull(mock.getGame("2"));
		assertNotNull(mock.getGame("3"));
	}

}
