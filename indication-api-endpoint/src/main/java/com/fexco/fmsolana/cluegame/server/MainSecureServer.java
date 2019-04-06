package com.fexco.fmsolana.cluegame.server;

import static spark.Spark.secure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Spark;

public class MainSecureServer {

	private static Logger logger = LoggerFactory.getLogger(MainSecureServer.class);

	public static void main(String[] args) {
		Spark.port(4568);
		configureSecureStore(args);
		ApiService.apiInit();
	}

	private static void configureSecureStore(String[] args) {
		String keystore = System.getProperty("sparkjava.secure.keystore.file");
		String keywork = System.getProperty("sparkjava.secure.keystore.keyword");
		if (keystore == null && keywork == null && args != null && args.length >= 2) {
			keystore = args[0];
			keywork = args[1];
		}
		if (keystore == null && keywork == null) {
			keystore = "deploy/keystore.jks";
			keywork = "password";
			logger.warn(getWarningMessage());
		}
		logger.info("search keystore in location:{}", keystore);
		secure(keystore, keywork, null, null);
	}

	private static String getWarningMessage() {
		StringBuilder text = new StringBuilder();
		text.append("Keystore  default only for test propousal");
		text.append("\nPlease specific the keystore:");
		text.append("\n by properties:");
		text.append("\n    -Dsparkjava.secure.keystore.file=route/keystore.jsk");
		text.append("\n    -Dsparkjava.secure.keystore.keyword=keyword\"");
		text.append("\n run params:");
		text.append("\n     java -cp server.jar rout/keysotre keyword\"");
		text.append("\n Properties have preferences about run params\"");
		return text.toString();
	}

}
