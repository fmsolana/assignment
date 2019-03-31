package com.fexco.fmsolana.cluegame.bean.game;

import java.util.List;

public class Game {

	private String gameId;
	private List<Clue> listClue;

	public Game(String gameId, List<Clue> listClue) {
		super();
		this.gameId = gameId;
		this.listClue = listClue;
	}

	public String getGameId() {
		return gameId;
	}

	public Clue getClue(int i) {
		return listClue.get(i);
	}

}
