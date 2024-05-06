package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LottoGameResult {

	public static final List<Integer> LOTTO_PRIZE_MATCHED_COUNT = new ArrayList<>(Arrays.asList(3, 4, 5, 6));
	public static final List<Integer> LOTTO_PRIZE_MONEY = new ArrayList<>(
		Arrays.asList(5000, 50000, 1500000, 30000000, 2000000000));

	private final WinningLotto winningLotto;
	private List<Integer> gameResult;
	private final int bonusNumber;

	public LottoGameResult(List<Integer> lottoWinningNumber, int bonusNumber, Lottos lottos) {
		this.winningLotto = new WinningLotto(lottoWinningNumber);
		this.bonusNumber = bonusNumber;
		calculateGameResult(lottos);
	}

	public void calculateGameResult(Lottos lottos) {
		List<Integer> gameResult = new ArrayList<>(Collections.nCopies(5, 0));
		lottos.getMatchedLottoCounts(winningLotto.getWinningLotto(), bonusNumber)
			.forEach((count, isContainsBonusNumber) -> {
				for (int i = 0; i < gameResult.size(); i++) {
					setGameResultByCount(count, isContainsBonusNumber, gameResult);
				}
			});
		this.gameResult = gameResult;
	}

	private void setGameResultByCount(int count, boolean isContainsBonusNumber, List<Integer> gameResult) {
		if (count == 3)
			gameResult.set(0, gameResult.get(0) + 1);
		else if (count == 4)
			gameResult.set(1, gameResult.get(1) + 1);
		else if (count == 5 && !isContainsBonusNumber)
			gameResult.set(2, gameResult.get(2) + 1);
		else if (count == 5)
			gameResult.set(3, gameResult.get(3) + 1);
		else if (count == 6)
			gameResult.set(4, gameResult.get(4) + 1);
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
