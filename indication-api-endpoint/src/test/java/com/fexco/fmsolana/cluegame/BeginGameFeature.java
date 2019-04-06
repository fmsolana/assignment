package com.fexco.fmsolana.cluegame;

import static com.fexco.fmsolana.cluegame.RunCucumberTest.getUrl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;

import com.fexco.fmsolana.cluegame.bean.game.Clue;
import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.bean.game.UserCheckTime;
import com.google.gson.Gson;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BeginGameFeature {

	private static final Gson gson = new Gson();
	private int port = RunCucumberTest.port;
	private String domain = RunCucumberTest.domain;
	private String protocol = RunCucumberTest.protocol;
	private HttpClient httpClient = RunCucumberTest.httpClient;

	private ContentResponse getGameResponse;

	private String reqUserId;
	private int reqGameId;

	private String jsonStartResponse;

	@When("get request with game {int}")
	public void get_request_to_with_game(int gameId)
			throws IOException, InterruptedException, ExecutionException, TimeoutException {
		getGameResponse = httpClient.GET(getUrl("/api/game/" + gameId));

	}

	@Then("a json respone with introductory explanation is send")
	public void a_json_respone_with_introductory_explanation_is_send() throws IOException {
		assertEquals("Must be Ok response", 200, getGameResponse.getStatus());
		String body = getGameResponse.getContentAsString();
		Game game = gson.fromJson(body, Game.class);
		assertNotNull(game);

	}

	@Given("an user {string} and game {int}")
	public void an_user_id_and_game_id(String userId, int gameId) {
		reqUserId = userId;
		reqGameId = gameId;
	}

	@When("a begin game post request come")
	public void a_begin_game_post_request_come()
			throws IOException, InterruptedException, TimeoutException, ExecutionException {
		jsonStartResponse = httpClient.POST(getUrl("/api/game/start/" + reqGameId + "/" + reqUserId))
				.content(new StringContentProvider("{ }"), "application/json").send().getContentAsString();
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
	public void service_for_coutdown_is_invoke()
			throws IOException, InterruptedException, ExecutionException, TimeoutException {
		// TODO How implement this type of Step?
		// The notification must be send by service and check here if it was sent
		Thread.sleep(1000);
		String checkTimeResponse = httpClient
				.GET(new URL("http", domain, port, "/api/game/check/time/" + reqGameId + "/" + reqUserId).toString())
				.getContentAsString();
		UserCheckTime userCheckTime = gson.fromJson(checkTimeResponse, UserCheckTime.class);
		assertNotNull(userCheckTime);
		long time = System.currentTimeMillis();
		assertTrue("It must have been more than a second", time - userCheckTime.getInitTime() > 1000);

	}

}
