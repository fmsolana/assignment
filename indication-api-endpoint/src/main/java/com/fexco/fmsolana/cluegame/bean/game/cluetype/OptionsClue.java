package com.fexco.fmsolana.cluegame.bean.game.cluetype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fexco.fmsolana.cluegame.bean.game.Clue;

public class OptionsClue extends Clue {

	private List<String> options = new ArrayList<>(3);

	private String correctOption;

	public OptionsClue(int id) {
		super(id);
	}

	public OptionsClue(int id, String option1, String option2, String correctOption) {
		super(id);
		options.add(option1);
		options.add(option2);
		options.add(correctOption);
		this.correctOption = correctOption;
	}

	@Override
	public boolean isAnswer(String answer) {
		return correctOption.equalsIgnoreCase(answer);
	}

	public List<String> getOptions() {
		List<String> shuffleOptions = new ArrayList<>();
		shuffleOptions.addAll(options);
		Collections.shuffle(shuffleOptions);
		return shuffleOptions;
	}

}
