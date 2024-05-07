package view;

import java.util.List;

import domain.Lotto;
import response.LottoGameResultResponse;

public class OutputView {

	private static void printNewLine() {
		System.out.println();
	}

	public static void printLottoPurchaseCount(final int manualLottoCount, final int autoLottoCount) {
		printNewLine();
		System.out.println(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.", manualLottoCount, autoLottoCount));
	}

	public static void printLottos(final List<Lotto> lottos) {
		lottos.forEach((lotto) -> System.out.println(lotto.getLotto()));
		printNewLine();
	}

	public static void printLottoGameResult(final LottoGameResultResponse gameResult) {
		printNewLine();
		List<Integer> prizeCount = gameResult.getGameResult();
		System.out.println("당첨 통계");
		System.out.println("---------");
		String[] prizes = {"3개 일치 (5000원)", "4개 일치 (50000원)", "5개 일치 (1500000원)", "5개 일치, 보너스 볼 일치(30000000원)",
			"6개 일치 (2000000000원)"};
		for (int i = 0; i < prizes.length; i++) {
			System.out.printf("%s - %d개%n", prizes[i], prizeCount.get(i));
		}
	}

	public static void printLottoGameProfit(final double gameProfit) {
		String message = String.format("총 수익률은 %.2f입니다.", Math.floor(gameProfit * 100) / 100.0);
		System.out.println(message);
	}
}
