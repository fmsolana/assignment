package com.fexco.fmsolana.cluegame.bean.game.cluetype;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OptionsClueTest {

	@Test
	public void testOptionsClue() {
		OptionsClue options = new OptionsClue(1, "op1", "op2", "correct");
		assertNotNull(options.getOptions());
		assertTrue(options.isAnswer("correct"));
	}

}
