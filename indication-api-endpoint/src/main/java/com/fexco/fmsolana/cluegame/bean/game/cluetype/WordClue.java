package com.fexco.fmsolana.cluegame.bean.game.cluetype;

import com.fexco.fmsolana.cluegame.bean.game.Clue;

public class WordClue extends Clue {

	private String answer;

	public WordClue(int id) {
		super(id);
	}

	public WordClue(int id, String answer) {
		super(id);
		this.answer = answer;
	}

	@Override
	public boolean isAnswer(String answer) {
		return this.answer.equalsIgnoreCase(answer);
	}

}
