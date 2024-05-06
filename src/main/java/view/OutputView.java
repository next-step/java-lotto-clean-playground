package view;

import java.util.List;

import domain.Lotto;

public class OutputView {

	private static void printNewLine() {
		System.out.println();
	}

	public static void printLottoPurchaseCount(final int lottoPurchaseCount) {
		printNewLine();
		System.out.println(String.format("%d개를 구매했습니다.", lottoPurchaseCount));
	}

	public static void printLottos(final List<Lotto> lottos) {
		lottos.forEach((lotto) -> System.out.println(lotto.getLotto()));
		printNewLine();
	}

	public static void printLottoGameResult(final List<Integer> gameResult) {
		printNewLine();
		String message = String.format("당첨 통계%n" +
				"---------%n" +
				"3개 일치 (5000원)- %d개%n" +
				"4개 일치 (50000원)- %d개%n" +
				"5개 일치 (1500000원)- %d개%n" +
				"6개 일치 (2000000000원)- %d개"
			, gameResult.get(0), gameResult.get(1), gameResult.get(2), gameResult.get(3));
		System.out.println(message);
	}

	public static void printLottoGameProfit(final double gameProfit) {
		String message = String.format("총 수익률은 %.2f입니다.", Math.floor(gameProfit * 100) / 100.0);
		System.out.println(message);
	}
}
