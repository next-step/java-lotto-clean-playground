package view;

import domain.Lotto;
import domain.LottoGame;
import domain.WinningNumbers;

import java.util.List;

public class OutputView {

    public static void printStartMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printAmountMessage(int lottoAmount) {
        System.out.println(lottoAmount + "개를 구매했습니다.");
    }

    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public static void printWinningNumbers(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            System.out.println("당첨 번호가 입력되지 않았습니다.");
        } else {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            System.out.println(numbers);
        }
    }

    public static void printWinningStatistics(int[] matchCounts, double profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + matchCounts[3] + "개");
        System.out.println("4개 일치 (50000원)- " + matchCounts[4] + "개");
        System.out.println("5개 일치 (1500000원)- " + matchCounts[5] + "개");
        System.out.println("6개 일치 (2000000000원)- " + matchCounts[6] + "개");
        System.out.printf("총 수익률은 %.2f 입니다.", profitRate);
        printProfitRate(profitRate);
    }

    public static void printProfitRate(double profitRate) {
        if (profitRate < 1) {
            System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
        if (profitRate > 1) {
            System.out.println("기준이 1이기 때문에 결과적으로 이득라는 의미임)");
        }
    }
}
