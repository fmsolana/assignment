package com.fexco.fmsolana.cluegame.server.operation;

import com.fexco.fmsolana.cluegame.bean.game.Clue;
import com.fexco.fmsolana.cluegame.bean.game.ClueAnswer;
import com.fexco.fmsolana.cluegame.bean.game.ClueAnswerVerify;
import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.repository.GameRepository;

public class AnwserRequest {

	public ClueAnswerVerify validAnswer(ClueAnswer clueAnswer) {

		Game game = GameRepository.getGame(clueAnswer.getGameId());
		Clue clue = game.getClue(clueAnswer.getClueId());
		boolean correct = clue.isAnswer(clueAnswer.getAnswer());
		if (correct)
			return getValidClueAnswerVerify(game, clue, clueAnswer.getUserId(), clueAnswer.getAnswer());
		else
			return null;
	}

	private ClueAnswerVerify getValidClueAnswerVerify(Game game, Clue clue, String userId, String userAnswer) {
		Clue nextClue = game.getNextClue(clue.getId());
		String giftId = null;
		if (nextClue == null)
			giftId = game.getGiftId();
		return new ClueAnswerVerify(userId, clue.getId(), userAnswer, true, game.getGameId(), nextClue, giftId);
	}

}
