package com.fexco.fmsolana.cluegame.server;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spark.Spark;

public class MainServerTest {

	private static int port = 4567;
	private static String endPoint = "127.0.0.1";

	private static HttpClient httpClient = new HttpClient();

	private String mediaTypeJson = "";

	@BeforeClass
	public static void startService() {
		Spark.port(port);
		MainServer.main(null);
		Spark.awaitInitialization();
	}

	@BeforeClass
	public static void startHttpClient() throws Exception {
		httpClient.start();
	}

	@AfterClass
	public static void stopHttpClient() throws Exception {
		httpClient.stop();
	}

	@AfterClass
	public static void stopServer() {
		Spark.stop();
		Spark.awaitStop();
	}

	@Test
	public void isWorkSmoke() throws IOException, InterruptedException, ExecutionException, TimeoutException {
		int status = httpClient.GET(new URL("http", endPoint, port, "/smoke").toString()).getStatus();
		assertNotEquals(404, status);
	}

	@Test
	public void getGameById() throws IOException, InterruptedException, ExecutionException, TimeoutException {
		ContentResponse response = httpClient.GET(new URL("http", endPoint, port, "/api/game/1").toString());
		assertEnpointExistAndResponseIsJson(response);
	}

	@Test
	public void startGame() throws MalformedURLException, InterruptedException, TimeoutException, ExecutionException {
		ContentResponse response = httpClient.POST(new URL("http", endPoint, port, "/api/game/start/1/1").toString())
				.send();
		assertEnpointExistAndResponseIsJson(response);
	}

	@Test
	public void validAnswer() throws MalformedURLException, InterruptedException, TimeoutException, ExecutionException {
		ContentResponse response = httpClient.POST(new URL("http", endPoint, port, "/api/answer").toString()).send();
		assertEnpointExistAndResponseIsJson(response);
	}

	@Test
	public void getGift() throws MalformedURLException, InterruptedException, TimeoutException, ExecutionException {
		ContentResponse response = httpClient.GET(new URL("http", endPoint, port, "/api/gift/1/1").toString());
		assertEnpointExistAndResponseIsJson(response);
	}

	private void assertEnpointExistAndResponseIsJson(ContentResponse response) {
		assertNotEquals(mediaTypeJson, response.getMediaType());
		assertNotEquals(404, response.getStatus());
	}

}
