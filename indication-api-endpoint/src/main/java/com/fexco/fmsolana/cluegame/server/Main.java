package com.fexco.fmsolana.cluegame.server;

import static spark.Spark.get;

import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.repository.GameRepository;
import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {

		get("/smoke", (req, res) -> {
			return true;
		});
		get("/game/:id", (req, res) -> {
			String gameId = req.params("id");
			Game game = GameRepository.getGame(gameId);
			res.type("application/json");
			return new Gson().toJson(game);
		});
	}
}
