package com.fexco.fmsolana.cluegame.bean.game.cluetype;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WordClueTest {

	@Test
	public void testWordClue() {
		WordClue wordClue = new WordClue(1, "answer");
		assertTrue(wordClue.isAnswer("answer"));
	}
}
