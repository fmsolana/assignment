package com.fexco.fmsolana.cluegame.repository.dao.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fexco.fmsolana.cluegame.repository.dao.GameDao;
import com.fexco.fmsolana.cluegame.repository.entity.GameVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MockGameDaoImpl implements GameDao {

	Map<String, GameVO> games = new HashMap<>();

	public MockGameDaoImpl() {
		games = getMapGames();
	}

	@Override
	public GameVO getGame(String gameId) {
		return games.get(gameId);
	}

	private Map<String, GameVO> getMapGames() {
		try {
			Gson gson = new Gson();
			InputStream stream = this.getClass().getResourceAsStream("MockGameDao.json");
			List<String> json = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.toList());
			String result = String.join("", json);
			List<GameVO> listGames = gson.fromJson(result, new TypeToken<ArrayList<GameVO>>() {
			}.getType());
			return listGames.stream().collect(Collectors.toMap(GameVO::getGameId, p -> p));
		} catch (Exception e) {
			System.err.println("Error to load file MockGameDao.json from class path");
			return null;
		}
	}

}
