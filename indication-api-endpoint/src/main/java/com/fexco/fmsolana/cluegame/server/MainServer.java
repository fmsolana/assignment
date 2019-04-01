package com.fexco.fmsolana.cluegame.server;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

import com.fexco.fmsolana.cluegame.bean.game.ClueAnswer;
import com.fexco.fmsolana.cluegame.server.operation.AnwserRequest;
import com.fexco.fmsolana.cluegame.server.operation.GameRequest;
import com.fexco.fmsolana.cluegame.server.operation.GiftRequest;
import com.google.gson.Gson;

public class MainServer {

	private static Gson gson = new Gson();

	private static GameRequest gameRequest = new GameRequest();
	private static AnwserRequest anwserRequest = new AnwserRequest();
	private static GiftRequest giftRequest = new GiftRequest();

	public static void main(String[] args) {

		get("/smoke", (req, res) -> Boolean.TRUE, gson::toJson);
		path("/api", () -> {
			apiInit();
			after("/*", (req, res) -> {
				res.type("application/json");
			});
		});
	}

	private static void apiInit() {
		get("/game/:id", (req, res) -> gameRequest.getGameById(req.params("id")), gson::toJson);
		post("/game/start/:id/:user", (req, res) -> gameRequest.startGame(req.params("id"), req.params("user")),
				gson::toJson);
		post("/answer", (req, res) -> anwserRequest.validAnswer(gson.fromJson(req.body(), ClueAnswer.class)),
				gson::toJson);
		get("/gift/:giftId/:userId", (req, res) -> giftRequest.getGift(req.params("giftId"), req.params("userId")),
				gson::toJson);
	}
}
