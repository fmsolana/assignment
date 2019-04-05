package com.fexco.fmsolana.cluegame.repository.dao;

import com.fexco.fmsolana.cluegame.repository.entity.GameVO;

public interface GameDao {

	GameVO getGame(String gameId);

}
