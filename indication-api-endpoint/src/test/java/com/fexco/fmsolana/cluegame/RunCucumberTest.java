package com.fexco.fmsolana.cluegame;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jetty.client.HttpClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.fexco.fmsolana.cluegame.server.MainServer;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import spark.Spark;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber",
		"json:target/cucumber.json" }, features = "classpath:features", strict = true)
public class RunCucumberTest {

	protected static int port = 4567;
	protected static String domain = "127.0.0.1";
	protected static String protocol = "http";

	protected static HttpClient httpClient = new HttpClient();

	@BeforeClass
	public static void startService() {
		Spark.port(port);
		MainServer.main(null);
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

	public static String getUrl(String path) throws MalformedURLException {
		return new URL(protocol, domain, port, path).toString();
	}
}
