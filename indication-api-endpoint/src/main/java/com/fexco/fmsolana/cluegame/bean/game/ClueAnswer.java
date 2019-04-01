package com.fexco.fmsolana.cluegame.bean.game;

public class ClueAnswer {

	private String userId;
	private String gameId;
	private String clueId;
	private String answer;

	public ClueAnswer(String userId, String gameId, String clueId, String answer) {
		super();
		this.userId = userId;
		this.gameId = gameId;
		this.clueId = clueId;
		this.answer = answer;
	}

	public String getUserId() {
		return userId;
	}

	public String getGameId() {
		return gameId;
	}

	public String getClueId() {
		return clueId;
	}

	public String getAnswer() {
		return answer;
	}

}
