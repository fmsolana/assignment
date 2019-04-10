package com.fexco.fmsolana.cluegame.server.operation.validation;

import org.junit.Test;

import com.fexco.fmsolana.cluegame.server.exceptions.GameRequestException;

public class ParamValidationTest {

	private ParamValidation validation = new ParamValidation();

	@Test(expected = GameRequestException.class)
	public void testValidationHasSpaces() throws GameRequestException {
		validation.validateParam(" ");
	}

	@Test(expected = GameRequestException.class)
	public void testValidation() throws GameRequestException {
		validation.validateParam(null);
	}

	@Test(expected = GameRequestException.class)
	public void testValidationTooLong() throws GameRequestException {
		validation.validateParam("thistextistoolong");
	}

	public void testValidationGoodParam() throws GameRequestException {
		validation.validateParam("goodParam");
	}

}
