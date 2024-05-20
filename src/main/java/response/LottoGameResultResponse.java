package response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.LottoPrize;

public class LottoGameResultResponse {

	private final Map<LottoPrize, Integer> lottoGameResultResponse = new HashMap<>();

	public LottoGameResultResponse(List<LottoPrize> lottoPrizes) {
		for (LottoPrize lottoPrize : LottoPrize.values()) {
			if (!lottoPrize.equals(lottoPrize.LAST_PRIZE)) {
				lottoGameResultResponse.put(lottoPrize, 0);
			}
		}
		for (LottoPrize lottoPrize : lottoPrizes) {
			if (!lottoPrize.equals(lottoPrize.LAST_PRIZE)) {
				lottoGameResultResponse.put(lottoPrize, lottoGameResultResponse.get(lottoPrize) + 1);
			}
		}
	}

	public Map<LottoPrize, Integer> getGameResult() {
		return Map.copyOf(lottoGameResultResponse);
	}
}
