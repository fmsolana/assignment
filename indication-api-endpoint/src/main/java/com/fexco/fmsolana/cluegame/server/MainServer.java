package com.fexco.fmsolana.cluegame.server;

import spark.Spark;

public class MainServer {

	public static void main(String[] args) {
		Spark.port(4567);
		ApiService.apiInit();
	}

}
