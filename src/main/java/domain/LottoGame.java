package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LottoGame {
	public static final int LOTTO_LENGTH = 6;
	public static final int LOTTO_MAX_NUMBER = 45;
	public static final List<Integer> LOTTO_PRIZE_MATCHED_COUNT = new ArrayList<>(Arrays.asList(3, 4, 5, 6));
	public static final List<Integer> LOTTO_PRIZE_MONEY = new ArrayList<>(
		Arrays.asList(5000, 50000, 1500000, 2000000000));

	private List<Integer> lottoWinningNumber;
	private List<Integer> gameResult;
	private final Lottos lottos;

	private final int purchaseAmount;
	private final int lottoCount;

	public LottoGame(int purchaseAmount) {
		if (purchaseAmount < 1000) {
			throw new IllegalArgumentException("로또 1장의 가격은 1000원 입니다.");
		}
		this.purchaseAmount = purchaseAmount;
		this.lottoCount = purchaseAmount / 1000;
		this.lottos = new Lottos(lottoCount);
	}

	public void calculateGameResult(List<Integer> lottoWinningNumber) {
		this.lottoWinningNumber = lottoWinningNumber;
		List<Integer> gameResult = new ArrayList<>(Collections.nCopies(4, 0));
		lottos.getMatchedLottoCounts(lottoWinningNumber).forEach((count) -> {
			for (int i = 0; i < LOTTO_PRIZE_MATCHED_COUNT.size(); i++) {
				if (count == LOTTO_PRIZE_MATCHED_COUNT.get(i)) {
					gameResult.set(i, gameResult.get(i) + 1);
				}
			}
		});
		this.gameResult = gameResult;
	}

	public double calculateGameProfit() {
		int totalProfit = 0;
		for (int i = 0; i < LOTTO_PRIZE_MATCHED_COUNT.size(); i++) {
			totalProfit += gameResult.get(i).intValue() * LOTTO_PRIZE_MONEY.get(i);
		}
		return ((double)totalProfit / purchaseAmount);
	}

	public int getLottoCount() {
		return lottoCount;
	}

	public List<Integer> getGameResult() {
		return gameResult;
	}

}
