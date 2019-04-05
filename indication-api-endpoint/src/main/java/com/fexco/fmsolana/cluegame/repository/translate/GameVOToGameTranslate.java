package com.fexco.fmsolana.cluegame.repository.translate;

import java.util.List;
import java.util.stream.Collectors;

import com.fexco.fmsolana.cluegame.bean.game.Clue;
import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.bean.game.cluetype.CorrectPlaceClue;
import com.fexco.fmsolana.cluegame.bean.game.cluetype.OptionsClue;
import com.fexco.fmsolana.cluegame.bean.game.cluetype.WordClue;
import com.fexco.fmsolana.cluegame.repository.entity.ClueVO;
import com.fexco.fmsolana.cluegame.repository.entity.GameVO;

public class GameVOToGameTranslate {

	public Game transformToGame(GameVO gameVo) {
		if (gameVo == null)
			return null;
		return new Game(gameVo.getGameId(), transformToListClue(gameVo.getListClue()), gameVo.getGiftId());
	}

	private List<Clue> transformToListClue(List<ClueVO> listClue) {
		return listClue.stream().map(gameVo -> transformToClue(gameVo)).collect(Collectors.toList());
	}

	private Clue transformToClue(ClueVO gameVo) {
		if (gameVo.getOption1() != null && gameVo.getOption2() != null && gameVo.getCorrectOption() != null)
			return new OptionsClue(gameVo.getId(), gameVo.getOption1(), gameVo.getOption2(), gameVo.getCorrectOption());
		if (gameVo.getAnswer() != null)
			return new WordClue(gameVo.getId(), gameVo.getAnswer());
		if (gameVo.getLatitud() != null && gameVo.getLongitud() != null)
			return new CorrectPlaceClue(gameVo.getId(), gameVo.getLongitud(), gameVo.getLatitud());
		return null;
	}
}
