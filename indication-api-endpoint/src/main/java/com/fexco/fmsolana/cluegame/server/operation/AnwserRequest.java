package com.fexco.fmsolana.cluegame.server.operation;

import com.fexco.fmsolana.cluegame.bean.game.Clue;
import com.fexco.fmsolana.cluegame.bean.game.ClueAnswer;
import com.fexco.fmsolana.cluegame.bean.game.ClueAnswerVerify;
import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.repository.GameRepository;

public class AnwserRequest {

	public ClueAnswerVerify validAnswer(ClueAnswer clueAnswer) {
		if (clueAnswer == null)
			return null;
		Game game = GameRepository.getGame(clueAnswer.getGameId());
		if (game == null)
			return null;
		Clue clue = game.getClue(clueAnswer.getClueId());
		boolean correct = false;
		if (clue != null)
			correct = clue.isAnswer(clueAnswer.getAnswer());
		if (correct)
			return getValidClueAnswerVerify(game, clue, clueAnswer.getUserId(), clueAnswer.getAnswer());
		else
			return getInvalidClueAnswerVerify(game, clue, clueAnswer.getUserId(), clueAnswer.getAnswer());
	}

	private ClueAnswerVerify getValidClueAnswerVerify(Game game, Clue clue, String userId, String userAnswer) {
		Clue nextClue = game.getNextClue(clue.getId() + 1);
		String giftId = null;
		if (nextClue == null)
			giftId = game.getGiftId();
		return new ClueAnswerVerify(userId, clue.getId(), userAnswer, true, game.getGameId(), nextClue, giftId);
	}

	private ClueAnswerVerify getInvalidClueAnswerVerify(Game game, Clue clue, String userId, String userAnswer) {
		Clue sameClue;
		if (clue != null)
			sameClue = game.getNextClue(clue.getId());
		else // Wrong clue number go to first
			sameClue = game.getNextClue(1);

		return new ClueAnswerVerify(userId, sameClue.getId(), userAnswer, false, game.getGameId(), sameClue, null);
	}

}
