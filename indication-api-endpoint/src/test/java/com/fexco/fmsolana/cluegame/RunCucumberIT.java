package com.fexco.fmsolana.cluegame;

import org.eclipse.jetty.client.HttpClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber",
		"json:target/cucumber.json" }, features = "classpath:features")
public class RunCucumberIT {

	@BeforeClass
	public static void startHttpClient() throws Exception {
		RunCucumberTest.httpClient = new HttpClient();
		RunCucumberTest.httpClient.start();
	}

	@AfterClass
	public static void stopHttpClient() throws Exception {
		RunCucumberTest.httpClient.stop();
	}
}
