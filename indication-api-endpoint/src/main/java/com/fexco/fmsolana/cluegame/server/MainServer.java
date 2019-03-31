package com.fexco.fmsolana.cluegame.server;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

import com.fexco.fmsolana.cluegame.server.operation.GameRequest;
import com.google.gson.Gson;

public class MainServer {

	private static Gson gson = new Gson();

	private static GameRequest gameRequest = new GameRequest();

	public static void main(String[] args) {

		get("/smoke", (req, res) -> Boolean.TRUE, gson::toJson);
		path("/api", () -> {
			get("/game/:id", (req, res) -> gameRequest.getGameById(req.params("id")), gson::toJson);
			post("/game/start/:id/:user", (req, res) -> gameRequest.startGame(req.params("id"), req.params("user")),
					gson::toJson);
			after("/*", (req, res) -> {
				res.type("application/json");
			});
		});
	}
}
