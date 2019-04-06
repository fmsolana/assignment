package com.fexco.fmsolana.cluegame;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fexco.fmsolana.cluegame.bean.game.UserCheckTime;
import com.fexco.fmsolana.cluegame.service.CountDownService;
import com.fexco.fmsolana.cluegame.service.UserNotifierService;
import com.fexco.fmsolana.cluegame.service.impl.CountDownServiceImpl;
import com.fexco.fmsolana.cluegame.service.impl.UserNotifierServiceImpl;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TipsCountdownPushFeature {

	private UserCheckTime userCheckTime;
	private UserCheckTime recvUserCheckTime;
	private String userId;
	long message;
	Integer timeWait;
	boolean sent = false;

	private CountDownService countDownService = new CountDownServiceImpl();
	private UserNotifierService userNotifierService = new UserNotifierServiceImpl();

	@Given("a message")
	public void a_message() {
		message = System.currentTimeMillis();

	}

	@Given("subscriptor {string}")
	public void subscriptor(String userId) {
		this.userId = userId;
		userCheckTime = new UserCheckTime(message, userId);
	}

	@Given("time {int}")
	public void time(Integer time) {
		timeWait = time;
		assertNotNull(timeWait);
	}

	@When("the service is call")
	public void the_service_is_call() {
		sent = countDownService.pushNotification(userCheckTime);
	}

	@Then("push a message for subciptor with time in queu")
	public void push_a_message_for_subciptor_with_time_in_queu() {
		assertTrue(sent);
	}

	@Given("a message in queue from an {string}")
	public void a_message_in_queue_from_an(String userId) {
		recvUserCheckTime = countDownService.getNotification(userId);
		assertNotNull(recvUserCheckTime);
	}

	@When("the time over")
	public void the_time_over() {
		long total = System.currentTimeMillis() - recvUserCheckTime.getInitTime();
		assertTrue(total > 1000);
	}

	@Then("subscriptor must be notify")
	public void subscriptor_must_be_notify() {
		assertTrue(userNotifierService.pushNotification(userCheckTime));
	}

}
