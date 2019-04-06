package com.fexco.fmsolana.cluegame.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.junit.Test;

public abstract class testServer {

	private String mediaTypeJson = "application/json";

	public abstract HttpClient getHttClient();

	public abstract String getProtocol();

	public abstract String getEndPoint();

	public abstract int getPort();

	@Test
	public void isWorkSmoke() throws IOException, InterruptedException, ExecutionException, TimeoutException {

		int status = getHttClient().GET(new URL(getProtocol(), getEndPoint(), getPort(), "/smoke").toString())
				.getStatus();
		assertNotEquals(404, status);
	}

	@Test
	public void getGameById() throws IOException, InterruptedException, ExecutionException, TimeoutException {
		ContentResponse response = getHttClient()
				.GET(new URL(getProtocol(), getEndPoint(), getPort(), "/api/game/1").toString());
		assertEnpointExistAndResponseIsJson(response);
	}

	@Test
	public void startGame() throws MalformedURLException, InterruptedException, TimeoutException, ExecutionException {
		ContentResponse response = getHttClient()
				.POST(new URL(getProtocol(), getEndPoint(), getPort(), "/api/game/start/1/1").toString()).send();
		assertEnpointExistAndResponseIsJson(response);
	}

	@Test
	public void validAnswer() throws MalformedURLException, InterruptedException, TimeoutException, ExecutionException {
		ContentResponse response = getHttClient()
				.POST(new URL(getProtocol(), getEndPoint(), getPort(), "/api/answer").toString()).send();
		assertEnpointExistAndResponseIsJson(response);
	}

	@Test
	public void getGift() throws MalformedURLException, InterruptedException, TimeoutException, ExecutionException {
		ContentResponse response = getHttClient()
				.GET(new URL(getProtocol(), getEndPoint(), getPort(), "/api/gift/1/1").toString());
		assertEnpointExistAndResponseIsJson(response);
	}

	@Test
	public void checkTime() throws MalformedURLException, InterruptedException, ExecutionException, TimeoutException {
		ContentResponse response = getHttClient()
				.GET(new URL(getProtocol(), getEndPoint(), getPort(), "/api/game/check/time/1/1").toString());
		assertEnpointExistAndResponseIsJson(response);

	}

	private void assertEnpointExistAndResponseIsJson(ContentResponse response) {
		assertEquals(mediaTypeJson, response.getMediaType());
		assertNotEquals(404, response.getStatus());
	}
}
