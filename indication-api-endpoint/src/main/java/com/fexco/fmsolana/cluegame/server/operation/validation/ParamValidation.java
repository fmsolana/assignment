package com.fexco.fmsolana.cluegame.server.operation.validation;

import com.fexco.fmsolana.cluegame.server.exceptions.GameRequestException;

public class ParamValidation {

	public void validateParam(String param) throws GameRequestException {
		if (param == null || param.contains(" ") || param.length() > 10)
			throw new GameRequestException("not id Allow " + param);
	}
}
