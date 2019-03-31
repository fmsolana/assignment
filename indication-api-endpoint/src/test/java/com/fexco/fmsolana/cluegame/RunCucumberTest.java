package com.fexco.fmsolana.cluegame;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.fexco.fmsolana.cluegame.server.Main;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import spark.Spark;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber",
		"json:target/cucumber.json" }, features = "classpath:features",strict=true)
public class RunCucumberTest {
	
	
	public static int port = 4567;

	@BeforeClass
	public static void startService() {
		Spark.port(port);
		Main.main(null);
	}

	@AfterClass
	public static void stopServer() {
		Spark.stop();
	}
}
