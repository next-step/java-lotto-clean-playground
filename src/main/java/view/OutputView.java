package view;

import java.util.Comparator;
import java.util.List;

import domain.Lotto;
import domain.LottoPrize;
import response.LottoGameResultResponse;

public class OutputView {

	private static final String LOTTO_PURCHASE_FORMAT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
	private static final String MATCH_RESULT_FORMAT = "%d개 일치 (%d원) - %d개";
	private static final String MATCH_RESULT_SECOND_PRIZE_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
	private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.2f입니다.";

	private static void printNewLine() {
		System.out.println();
	}

	public static void printLottoPurchaseCount(final int manualLottoCount, final int autoLottoCount) {
		printNewLine();
		System.out.printf(LOTTO_PURCHASE_FORMAT, manualLottoCount, autoLottoCount);
		printNewLine();
	}

	public static void printLottos(final List<Lotto> lottos) {
		lottos.forEach((lotto) -> System.out.println(lotto.getLotto()));
		printNewLine();
	}

	public static void printLottoGameResult(final LottoGameResultResponse lottoGameResultResponse) {
		printNewLine();
		System.out.println("당첨 통계");
		System.out.println("---------");
		lottoGameResultResponse
			.getGameResult()
			.entrySet()
			.stream()
			.sorted(Comparator.comparing(entry -> entry.getKey().ordinal()))
			.forEach(entry -> {
				LottoPrize prize = entry.getKey();
				int count = entry.getValue();
				if (prize == LottoPrize.SECOND_PRIZE) {
					System.out.printf(MATCH_RESULT_SECOND_PRIZE_FORMAT, prize.getMatchCount(), prize.getPrice(), count);
				} else if (prize != LottoPrize.SECOND_PRIZE) {
					System.out.printf(MATCH_RESULT_FORMAT, prize.getMatchCount(), prize.getPrice(), count);
				}
				printNewLine();
			});
	}

	public static void printLottoGameProfit(final double gameProfitRate) {
		System.out.printf(PROFIT_RATE_FORMAT, gameProfitRate);
	}
}
