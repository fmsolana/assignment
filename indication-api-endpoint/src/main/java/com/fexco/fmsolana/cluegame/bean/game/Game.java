package com.fexco.fmsolana.cluegame.bean.game;

import java.util.List;

public class Game {

	private String gameId;
	private List<Clue> listClue;
	private String giftId;

	public Game(String gameId, List<Clue> listClue, String giftId) {
		super();
		this.gameId = gameId;
		this.listClue = listClue;
		this.giftId = giftId;
	}

	public String getGameId() {
		return gameId;
	}

	public Clue getClue(int i) {
		return listClue.get(i - 1);
	}

	public Clue getNextClue(int id) {
		if (id < listClue.size())
			return getClue(id);
		return null;
	}

	public String getGiftId() {
		return giftId;
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", listClue=" + listClue + ", giftId=" + giftId + "]";
	}

}
