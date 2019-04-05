package com.fexco.fmsolana.cluegame.bean.game.cluetype;

import java.util.Objects;

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

	@Override
	public String toString() {
		return "WordClue [answer=" + answer + ", getId()=" + getId() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(answer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordClue other = (WordClue) obj;
		return Objects.equals(answer, other.answer);
	}

}
