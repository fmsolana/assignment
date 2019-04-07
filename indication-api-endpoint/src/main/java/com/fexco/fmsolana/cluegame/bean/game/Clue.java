package com.fexco.fmsolana.cluegame.bean.game;

public class Clue {

	private int id;
	protected String typeClue = "GenericClue";

	public Clue(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getTypeClue() {
		return typeClue;
	}

	public boolean isAnswer(String answer) {
		return false;
	}

}
