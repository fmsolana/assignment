package com.fexco.fmsolana.cluegame.repository.translate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fexco.fmsolana.cluegame.bean.game.Game;
import com.fexco.fmsolana.cluegame.bean.game.cluetype.CorrectPlaceClue;
import com.fexco.fmsolana.cluegame.bean.game.cluetype.OptionsClue;
import com.fexco.fmsolana.cluegame.bean.game.cluetype.WordClue;
import com.fexco.fmsolana.cluegame.repository.entity.ClueVO;
import com.fexco.fmsolana.cluegame.repository.entity.GameVO;

public class GameVOToGameTranslateTest {
	private GameVOToGameTranslate gameVOToGameTranslate = new GameVOToGameTranslate();

	@Test
	public void testTransformToGame() {
		String gameId = "gameId";
		String giftId = "giftId";
		List<ClueVO> listClue = Arrays.asList(new ClueVO(1, "option1", "option2", "correctOption", null, null, null),
				new ClueVO(2, null, null, null, "answer", null, null),
				new ClueVO(3, null, null, null, null, 23.34f, 27.34f));
		GameVO gameVo = new GameVO(gameId, listClue, giftId);
		Game game = gameVOToGameTranslate.transformToGame(gameVo);
		assertNotNull(game);
		assertEquals(gameId, game.getGameId());
		assertEquals(giftId, game.getGiftId());
		assertEquals(new OptionsClue(1, "option1", "option2", "correctOption"), game.getClue(1));
		assertEquals(new WordClue(1, "answer"), game.getClue(2));
		assertEquals(new CorrectPlaceClue(3, 23.34f, 27.34f), game.getClue(3));
	}

	@Test
	public void testWithWrongDataTransformToGame() {
		String gameId = "gameId";
		String giftId = "giftId";
		List<ClueVO> listClue = Arrays.asList(new ClueVO(1, "option1", "option2", "correctOption", null, null, null),
				new ClueVO(2, null, null, null, null, null, null),
				new ClueVO(3, null, null, null, null, 23.34f, 27.34f));
		GameVO gameVo = new GameVO(gameId, listClue, giftId);
		Game game = gameVOToGameTranslate.transformToGame(gameVo);
		assertNotNull(game);
		assertEquals(gameId, game.getGameId());
		assertEquals(giftId, game.getGiftId());
		assertEquals(new OptionsClue(1, "option1", "option2", "correctOption"), game.getClue(1));
		assertNull(game.getClue(2));
		assertEquals(new CorrectPlaceClue(3, 23.34f, 27.34f), game.getClue(3));
	}
}
