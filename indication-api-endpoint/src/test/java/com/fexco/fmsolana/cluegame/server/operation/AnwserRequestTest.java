package com.fexco.fmsolana.cluegame.server.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fexco.fmsolana.cluegame.bean.game.ClueAnswer;
import com.fexco.fmsolana.cluegame.bean.game.ClueAnswerVerify;

public class AnwserRequestTest {

	private AnwserRequest AnwserRequest = new AnwserRequest();

	@Test
	public void testValidAnswer() {

		String userId = "1";
		String gameId = "1";
		int clueId = 1;
		String answer = "correctOption";
		ClueAnswer clueAnswer = new ClueAnswer(userId, gameId, clueId, answer);

		ClueAnswerVerify clueAnswerVerify = AnwserRequest.validAnswer(clueAnswer);
		assertNotNull(clueAnswerVerify);
		assertTrue(clueAnswerVerify.isValidAnswer());
		assertEquals(userId, clueAnswerVerify.getUserId());
		assertEquals(gameId, clueAnswerVerify.getGameId());
		assertEquals(clueId, clueAnswerVerify.getClueId());
		assertNotNull("correctAnswer must return next clue", clueAnswerVerify.getNextClue());
		assertNull("is not time of gift", clueAnswerVerify.getGiftId());
	}

	@Test
	public void testInvalidRequestValidAnswer() {

		String userId = "1";
		String gameId = "NO_EXISTS";
		int clueId = 1;
		String answer = "correctOption";
		ClueAnswer clueAnswer = new ClueAnswer(userId, gameId, clueId, answer);

		ClueAnswerVerify clueAnswerVerify = AnwserRequest.validAnswer(clueAnswer);
		assertNull("if gameid no exists must be null", clueAnswerVerify);

	}

	@Test
	public void testInvalidAnswer() {

		String userId = "1";
		String gameId = "1";
		int clueId = 1;
		String answer = "incorrecOption";
		ClueAnswer clueAnswer = new ClueAnswer(userId, gameId, clueId, answer);

		ClueAnswerVerify clueAnswerVerify = AnwserRequest.validAnswer(clueAnswer);
		assertNotNull(clueAnswerVerify);
		assertFalse(clueAnswerVerify.isValidAnswer());

	}

	@Test
	public void testInvalidClueId() {

		String userId = "1";
		String gameId = "1";
		int clueId = 7;
		String answer = "incorrecOption";
		ClueAnswer clueAnswer = new ClueAnswer(userId, gameId, clueId, answer);

		ClueAnswerVerify clueAnswerVerify = AnwserRequest.validAnswer(clueAnswer);
		assertNotNull("if gameid no exists must be null", clueAnswerVerify);

	}

}
