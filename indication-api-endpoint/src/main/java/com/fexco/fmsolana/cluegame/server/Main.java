package com.fexco.fmsolana.cluegame.server;

import static spark.Spark.*;

import com.fexco.fmsolana.cluegame.repository.GameRepository;
import com.fexco.fmsolana.cluegame.server.operation.GameRequest;
import com.google.gson.Gson;

public class Main {

	private static Gson gson = new Gson();
	
	private static GameRequest gameRequest=new GameRequest();
	
	public static void main(String[] args) {
		
		get("/smoke", (req, res) -> Boolean.TRUE, gson::toJson);
		path("/api", () -> {
			get("/game/:id", (req, res) -> gameRequest.getGameById(req.params("id")), gson::toJson);
			after("/*",(req, res) -> {
				res.type("application/json");
			});
		});
	}
}
