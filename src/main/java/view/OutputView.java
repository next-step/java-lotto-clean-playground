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
		lottos.forEach((lotto) -> System.out.println(lotto));
	}
}
