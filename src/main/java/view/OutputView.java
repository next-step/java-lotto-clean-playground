package view;

import java.util.List;

import domain.Lotto;

public class OutputView {
	public static void printLottoPurchaseCount(final int lottoPurchaseCount) {
		System.out.println(String.format("%d개를 구매했습니다.", lottoPurchaseCount));
	}

	private static void printNewLine() {
		System.out.println();
	}

	public static void printLottos(final List<Lotto> lottos) {
		printNewLine();
		lottos.forEach(System.out::println);
	}
}
