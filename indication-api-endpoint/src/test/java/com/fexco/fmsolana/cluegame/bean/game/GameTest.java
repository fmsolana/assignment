package com.fexco.fmsolana.cluegame.bean.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Test;

import com.fexco.fmsolana.cluegame.bean.game.cluetype.CorrectPlaceClue;
import com.fexco.fmsolana.cluegame.bean.game.cluetype.OptionsClue;

public class GameTest {

	private Game game = new Game("test", Arrays.asList(new CorrectPlaceClue(1), new OptionsClue(2)), "giftId");

	@Test
	public void textNextClue() {
		assertNotNull(game.getNextClue(1));
		assertNotNull(game.getNextClue(2));
		assertNull(game.getNextClue(3));
	}

	@Test
	public void textClue() {
		assertNotNull(game.getClue(1));
		assertEquals(1, game.getClue(1).getId());
	}
}
