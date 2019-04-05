package com.fexco.fmsolana.cluegame.repository.entity;

public class ClueVO {

	private int id;

	private String option1;
	private String option2;
	private String correctOption;

	private String answer;

	private Float longitud;
	private Float latitud;

	public ClueVO(int id, String option1, String option2, String correctOption, String answer, Float longitud,
			Float latitud) {
		super();
		this.id = id;
		this.option1 = option1;
		this.option2 = option2;
		this.correctOption = correctOption;
		this.answer = answer;
		this.longitud = longitud;
		this.latitud = latitud;
	}

	public int getId() {
		return id;
	}

	public String getOption1() {
		return option1;
	}

	public String getOption2() {
		return option2;
	}

	public String getCorrectOption() {
		return correctOption;
	}

	public String getAnswer() {
		return answer;
	}

	public Float getLongitud() {
		return longitud;
	}

	public Float getLatitud() {
		return latitud;
	}

}
