package com.fexco.fmsolana.cluegame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fexco.fmsolana.cluegame.bean.game.Clue;
import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.http.tool.HttpReadBody;
import com.fexco.fmsolana.cluegame.http.tool.HttpRequest;
import com.google.gson.Gson;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BeginGameFeature {

	private static final Gson gson = new Gson();
	private int port = RunCucumberTest.port;
	private String domain = RunCucumberTest.domain;
	private String protocol = RunCucumberTest.protocol;

	private HttpURLConnection getGameResponse;

	private String reqUserId;
	private int reqGameId;

	private String jsonStartResponse;

	@When("get request with game {int}")
	public void get_request_to_with_game(int gameId) throws IOException {
		getGameResponse = (HttpURLConnection) new URL(protocol, domain, port, "/api/game/" + gameId).openConnection();

	}

	@Then("a json respone with introductory explanation is send")
	public void a_json_respone_with_introductory_explanation_is_send() throws IOException {
		assertEquals("Must be Ok response", 200, getGameResponse.getResponseCode());
		String body = HttpReadBody.StringBodyFromConection(getGameResponse);
		Game game = gson.fromJson(body, Game.class);
		assertNotNull(game);

	}

	@Given("an user {string} and game {int}")
	public void an_user_id_and_game_id(String userId, int gameId) {
		reqUserId = userId;
		reqGameId = gameId;
	}

	@When("a begin game post request come")
	public void a_begin_game_post_request_come() throws IOException {
		jsonStartResponse = HttpRequest.sendPost("http", domain, port,
				"/api/game/start/" + reqGameId + "/" + reqUserId);
	}

	@Then("the game start")
	public void the_game_start() {
		assertNotNull(jsonStartResponse);
	}

	@Then("a json respone is send with first clue")
	public void a_json_respone_is_send_with_first_clue() {
		Clue clue = gson.fromJson(jsonStartResponse, Clue.class);
		assertNotNull(clue);
		assertEquals(1, clue.getId());
	}

	@Then("service for coutdown is invoke")
	public void service_for_coutdown_is_invoke() {
		// Write code here that turns the phrase above into concrete actions
		throw new cucumber.api.PendingException();
	}

}
