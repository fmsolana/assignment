package com.fexco.fmsolana.cluegame.bean.game;

public class ClueAnswerVerify {

	private String userId;
	private int clueId;
	private String userAnswer;
	private boolean validAnswer;
	private String gameId;
	private Clue nextClue;
	private String giftId;

	public ClueAnswerVerify(String userId, int clueId, String userAnswer, boolean validAnswer, String gameId,
			Clue nextClue, String giftId) {
		super();
		this.userId = userId;
		this.clueId = clueId;
		this.userAnswer = userAnswer;
		this.validAnswer = validAnswer;
		this.gameId = gameId;
		this.nextClue = nextClue;
		this.giftId = giftId;
	}

	public String getUserId() {
		return userId;
	}

	public int getClueId() {
		return clueId;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public boolean isValidAnswer() {
		return validAnswer;
	}

	public String getGameId() {
		return gameId;
	}

	public Clue getNextClue() {
		return nextClue;
	}

	public String getGiftId() {
		return giftId;
	}

}
