package com.fexco.fmsolana.cluegame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;

import com.fexco.fmsolana.cluegame.bean.game.ClueAnswer;
import com.fexco.fmsolana.cluegame.bean.game.ClueAnswerVerify;
import com.fexco.fmsolana.cluegame.bean.game.Gift;
import com.google.gson.Gson;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FigureOutTheClueFeature {

	private Gson gson = new Gson();

	private String userId;
	private String gameId;
	private String clueId;
	private String answer;

	private ClueAnswerVerify clueAnswerVerify;

	private String giftId;

	private Gift gift;

	@Given("user {string}")
	public void user(String userId) {
		this.userId = userId;
	}

	@Given("game {string}")
	public void game(String gameId) {
		this.gameId = gameId;
	}

	@Given("clue {string}")
	public void clue(String clueId) {
		this.clueId = clueId;
	}

	@Given("the answer {string}")
	public void the_answer(String answer) {
		this.answer = answer;
	}

	@When("post request is come")
	public void post_request_is_come()
			throws InterruptedException, TimeoutException, ExecutionException, MalformedURLException {
		ContentResponse respuesta = RunCucumberTest.httpClient.POST(RunCucumberTest.getUrl("/api/answer"))
				.content(new StringContentProvider(gson.toJson(new ClueAnswer(userId, gameId, clueId, answer))),
						"application/json")
				.send();
		assertEquals(200, respuesta.getStatus());
		clueAnswerVerify = gson.fromJson(respuesta.getContentAsString(), ClueAnswerVerify.class);
	}

	@When("the game id is correct")
	public void the_game_id_is_correct() {
		assertNotNull(gameId);
		assertNotNull(clueAnswerVerify);
		assertEquals(gameId, clueAnswerVerify.getGameId());
	}

	@When("the user id is correct")
	public void the_user_id_is_correct() {
		assertNotNull(userId);
		assertNotNull(clueAnswerVerify);
		assertEquals(userId, clueAnswerVerify.getUserId());
	}

	@When("the clue id is correct")
	public void the_clue_id_is_correct() {
		assertNotNull(clueId);
		assertNotNull(clueAnswerVerify);
		assertEquals(clueId, clueAnswerVerify.getClueId());
	}

	@When("the answer is correct")
	public void the_answer_is_correct() {
		assertNotNull(answer);
		assertNotNull(clueAnswerVerify);
		assertTrue(clueAnswerVerify.isValid());
		assertEquals(answer, clueAnswerVerify.getAnswer());
	}

	@Then("a json with next clue is send")
	public void a_json_with_next_clue_is_send() {
		assertNotNull(clueAnswerVerify.getNextClue());
	}

	@Then("push message for countdown")
	public void push_message_for_countdown() {
		// Write code here that turns the phrase above into concrete actions
		throw new cucumber.api.PendingException();
	}

	@When("is the last clue")
	public void is_the_last_clue() {
		assertNull(clueAnswerVerify.getNextClue());
	}

	@Then("a json with the gift for winner")
	public void a_json_with_winner_for_winer() {
		assertNotNull(clueAnswerVerify);
		assertNotNull(clueAnswerVerify.getGiftId());
	}

	@Then("a gift id is generated is send")
	public void a_gift_id_is_generated() {
		assertNotNull(clueAnswerVerify.getGiftId());
	}

	@Given("gift {string}")
	public void gift(String giftId) {
		this.giftId = giftId;
	}

	@When("get request to retrieve the gift")
	public void get_request_to_retrieve_the_gift()
			throws InterruptedException, TimeoutException, ExecutionException, MalformedURLException {
		ContentResponse respuesta = RunCucumberTest.httpClient
				.GET(RunCucumberTest.getUrl("/api/gift/" + giftId + "/" + userId));
		assertEquals(200, respuesta.getStatus());
		gift = gson.fromJson(respuesta.getContentAsString(), Gift.class);
	}

	@When("gift id is correct")
	public void gift_id_is_correct() {
		assertNotNull(giftId);
	}

	@When("user id is correct")
	public void user_id_is_correct() {
		assertNotNull(userId);
	}

	@Then("json with gift is send")
	public void json_with_gift_is_send() {
		assertNotNull(gift);
	}
}
