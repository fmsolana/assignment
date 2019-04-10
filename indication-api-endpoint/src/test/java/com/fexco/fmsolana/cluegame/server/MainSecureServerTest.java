package com.fexco.fmsolana.cluegame.server;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import spark.Spark;

public class MainSecureServerTest extends testServer {

	private static int port = 4568;
	private String endPoint = "127.0.0.1";
	private String protocol = "https";

	private static HttpClient httpClient = new HttpClient(new SslContextFactory(true));

	@BeforeClass
	public static void startService() {
		Spark.port(port);
		MainSecureServer.main(null);
		Spark.awaitInitialization();
	}

	@AfterClass
	public static void stopServer() {
		Spark.stop();
		Spark.awaitStop();
	}

	@BeforeClass
	public static void startHttpClient() throws Exception {
		httpClient.start();
	}

	@AfterClass
	public static void stopHttpClient() throws Exception {
		httpClient.stop();
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
