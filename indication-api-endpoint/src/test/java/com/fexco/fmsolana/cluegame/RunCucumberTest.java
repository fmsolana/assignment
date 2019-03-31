package com.fexco.fmsolana.cluegame;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.fexco.fmsolana.cluegame.server.MainServer;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import spark.Spark;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber",
		"json:target/cucumber.json" }, features = "classpath:features",strict=true)
public class RunCucumberTest {
	
	
	protected static int port = 4568;
	protected static String domain = "127.0.0.1";

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
}
