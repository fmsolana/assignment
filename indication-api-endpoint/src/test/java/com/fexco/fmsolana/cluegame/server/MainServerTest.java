package com.fexco.fmsolana.cluegame.server;

import org.eclipse.jetty.client.HttpClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;

import spark.Spark;

@Ignore
public class MainServerTest extends testServer {

	private static int port = 4567;
	private String endPoint = "127.0.0.1";
	private String protocol = "http";

	private static HttpClient httpClient = new HttpClient();

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

	@Override
	public HttpClient getHttClient() {
		return httpClient;
	}

	@Override
	public String getProtocol() {
		return protocol;
	}

	@Override
	public String getEndPoint() {
		return endPoint;
	}

	@Override
	public int getPort() {
		return port;
	}

}
