package com.fexco.fmsolana.cluegame.repository.entity;

import java.util.List;

public class GameVO {

	private String gameId;
	private List<ClueVO> listClue;
	private String giftId;

	public GameVO(String gameId, List<ClueVO> listClue, String giftId) {
		super();
		this.gameId = gameId;
		this.listClue = listClue;
		this.giftId = giftId;
	}

	public String getGameId() {
		return gameId;
	}

	public String getGiftId() {
		return giftId;
	}

	public List<ClueVO> getListClue() {
		return listClue;
	}

}
