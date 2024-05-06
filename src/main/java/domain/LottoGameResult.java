package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LottoGameResult {

	public static final List<Integer> LOTTO_PRIZE_MATCHED_COUNT = new ArrayList<>(Arrays.asList(3, 4, 5, 6));
	public static final List<Integer> LOTTO_PRIZE_MONEY = new ArrayList<>(
		Arrays.asList(5000, 50000, 1500000, 2000000000));

	private final WinningLotto winningLotto;
	private List<Integer> gameResult;
	private final int bonusNumber;

	public LottoGameResult(List<Integer> lottoWinningNumber, int bonusNumber, Lottos lottos) {
		this.winningLotto = new WinningLotto(lottoWinningNumber);
		this.bonusNumber = bonusNumber;
		calculateGameResult(lottos);
	}

	public void calculateGameResult(Lottos lottos) {
		List<Integer> gameResult = new ArrayList<>(Collections.nCopies(4, 0));
		lottos.getMatchedLottoCounts(winningLotto.getWinningLotto()).forEach((count) -> {
			for (int i = 0; i < LOTTO_PRIZE_MATCHED_COUNT.size(); i++) {
				if (count == LOTTO_PRIZE_MATCHED_COUNT.get(i)) {
					gameResult.set(i, gameResult.get(i) + 1);
				}
			}
		});
		this.gameResult = gameResult;
	}

	public double calculateGameProfit(int purchaseAmount) {
		int totalProfit = 0;
		for (int i = 0; i < LOTTO_PRIZE_MATCHED_COUNT.size(); i++) {
			totalProfit += gameResult.get(i).intValue() * LOTTO_PRIZE_MONEY.get(i);
		}
		return ((double)totalProfit / purchaseAmount);
	}

	public List<Integer> getGameResult() {
		return gameResult;
	}
}
