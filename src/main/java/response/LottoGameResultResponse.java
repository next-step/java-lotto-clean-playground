package response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.LottoPrize;

public class LottoGameResultResponse {

	private final List<Integer> gameResult = new ArrayList<>(Collections.nCopies(LottoPrize.LOTTO_PRIZE_COUNT, 0));

	public LottoGameResultResponse(List<LottoPrize> lottoPrizes) {
		for (LottoPrize lottoPrize : lottoPrizes) {
			setGameResultResponse(lottoPrize);
		}
	}

	private void setGameResultResponse(LottoPrize lottoPrize) {
		if (lottoPrize.equals(LottoPrize.FIFTH_PRIZE)) {
			gameResult.set(0, gameResult.get(0) + 1);
		} else if (lottoPrize.equals(LottoPrize.FOURTH_PRIZE)) {
			gameResult.set(1, gameResult.get(1) + 1);
		} else if (lottoPrize.equals(LottoPrize.THIRD_PRIZE)) {
			gameResult.set(2, gameResult.get(2) + 1);
		} else if (lottoPrize.equals(LottoPrize.SECOND_PRIZE)) {
			gameResult.set(3, gameResult.get(3) + 1);
		} else if (lottoPrize.equals(LottoPrize.FIRST_PRIZE)) {
			gameResult.set(4, gameResult.get(4) + 1);
		}
	}

	public List<Integer> getGameResult() {
		return gameResult;
	}
}
