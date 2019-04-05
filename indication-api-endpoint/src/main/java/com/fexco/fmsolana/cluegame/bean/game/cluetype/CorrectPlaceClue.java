package com.fexco.fmsolana.cluegame.bean.game.cluetype;

import java.util.Objects;

import com.fexco.fmsolana.cluegame.bean.game.Clue;

public class CorrectPlaceClue extends Clue {

	private float longitud;
	private float latitud;

	public CorrectPlaceClue(int id) {
		super(id);
	}

	public CorrectPlaceClue(int id, float longitud, float latitud) {
		super(id);
		this.longitud = longitud;
		this.latitud = latitud;
	}

	@Override
	public boolean isAnswer(String answer) {
		try {
			String[] split = answer.split(";");
			return longitud == Float.valueOf(split[0]) && longitud == Float.valueOf(split[1]);
		} catch (Exception e) {
			return false;
		}
	}

	public float getLongitud() {
		return longitud;
	}

	public float getLatitud() {
		return latitud;
	}

	@Override
	public String toString() {
		return "CorrectPlaceClue [longitud=" + longitud + ", latitud=" + latitud + ", getId()=" + getId() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(latitud, longitud);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CorrectPlaceClue other = (CorrectPlaceClue) obj;
		return Float.floatToIntBits(latitud) == Float.floatToIntBits(other.latitud)
				&& Float.floatToIntBits(longitud) == Float.floatToIntBits(other.longitud);
	}

}
