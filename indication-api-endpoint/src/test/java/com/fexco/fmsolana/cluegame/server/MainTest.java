package com.fexco.fmsolana.cluegame.server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spark.Spark;

public class MainTest {

	private static int port = 4567;

	@BeforeClass
	public static void startService() {
		Spark.port(port);
		Main.main(null);
	}

	@AfterClass
	public static void stopServer() {
		Spark.stop();
	}

	@Test
	public void isWorkSmoke() throws IOException {
		HttpURLConnection con = (HttpURLConnection) new URL("http", "localhost", port, "/smoke").openConnection();
		int status = con.getResponseCode();
		assertEquals(200, status);
	}
	
	@Test
	public void getGameById() throws IOException {
		HttpURLConnection con = (HttpURLConnection) new URL("http", "localhost", port, "/api/game/1").openConnection();
		int status = con.getResponseCode();
		assertEquals(200, status);
	}

}
