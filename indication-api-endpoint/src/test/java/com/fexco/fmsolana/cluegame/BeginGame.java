package com.fexco.fmsolana.cluegame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.http.tool.HttpReadBody;
import com.google.gson.Gson;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BeginGame {

	private static final Gson gson = new Gson();
	private int port = RunCucumberTest.port;
	private String domain= RunCucumberTest.domain;

	private HttpURLConnection getGameResponse;
	
	@When("get request with game {string}")
	public void get_request_to_with_game(String gameId) throws IOException {
		getGameResponse = (HttpURLConnection) new URL("http", domain, port, "/api/game/" + gameId)
				.openConnection();

	}

	@Then("a json respone with introductory explanation is send")
	public void a_json_respone_with_introductory_explanation_is_send() throws IOException {
		assertEquals("Must be Ok response",200, getGameResponse.getResponseCode());
		String body = HttpReadBody.StringBodyFromConection(getGameResponse);
		Game game = gson.fromJson(body, Game.class);
		assertNotNull(game);
		
	}

	@Given("an user id and game id")
	public void an_user_id_and_game_id() {
		// Write code here that turns the phrase above into concrete actions
		throw new cucumber.api.PendingException();
	}

	@When("a post request come")
	public void a_post_request_come() {
		// Write code here that turns the phrase above into concrete actions
		throw new cucumber.api.PendingException();
	}

	@Then("the game start")
	public void the_game_start() {
		// Write code here that turns the phrase above into concrete actions
		throw new cucumber.api.PendingException();
	}

	@Then("a json respone is send with first clue")
	public void a_json_respone_is_send_with_first_clue() {
		// Write code here that turns the phrase above into concrete actions
		throw new cucumber.api.PendingException();
	}

	@Then("service for coutdown is invoke")
	public void service_for_coutdown_is_invoke() {
		// Write code here that turns the phrase above into concrete actions
		throw new cucumber.api.PendingException();
	}

}
