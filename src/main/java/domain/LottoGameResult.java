package domain;

import java.util.List;
import java.util.stream.Collectors;

import response.LottoGameResultResponse;

public class LottoGameResult {

	private final WinningLotto winningLotto;
	private List<LottoPrize> gameResult;

	public LottoGameResult(Lotto lottoWinningNumber, LottoNumber bonusNumber) {
		this.winningLotto = new WinningLotto(lottoWinningNumber, bonusNumber);
	}

	public void calculateGameResult(Lottos lottos) {
		this.gameResult = lottos.getLottos()
			.stream()
			.map(lotto -> LottoPrize.of(lotto.getMatchedNumberCount(winningLotto),
				lotto.isContainsBonusNumber(winningLotto.getBonusNumber())))
			.collect(Collectors.toList());
	}

	public double calculateGameProfitRate(LottoPurchaseAmount purchaseAmount) {
		int totalProfit = 0;
		for (LottoPrize lottoPrize : gameResult) {
			totalProfit += lottoPrize.getPrice();
		}
		return Math.floor(((double)totalProfit / purchaseAmount.getPurchaseAmount()) * 100) / 100.0;
	}

	public LottoGameResultResponse getGameResult() {
		return new LottoGameResultResponse(List.copyOf(gameResult));
	}
}
