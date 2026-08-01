package view;

import domain.LottoTickets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class OutputView {
    private OutputView() {

    }

    public static void printLottoCount(int lottoCount) {
        System.out.println(lottoCount + "개를 구매했습니다");
        System.out.println();
    }

    public static void printLottoNumbers(LottoTickets lottoTickets) {
        for(int i = 0; i< lottoTickets.getSize(); i++) {
            System.out.println(lottoTickets.getLottoTreeSet(i));
        }
    }

    public static void printMatchCount(Map<Integer, Integer> matchStatistics) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        System.out.println("3개 일치 (5000원)- " + matchStatistics.get(3) + "개");
        System.out.println("4개 일치 (50000원)- " + matchStatistics.get(4) + "개");
        System.out.println("5개 일치 (1500000원)- " + matchStatistics.get(5) + "개");
        System.out.println("6개 일치 (2000000000원)- " + matchStatistics.get(6) + "개");
    }

    public static void printRateOfReturn(double rateOfReturn) {
        System.out.printf("총 수익률은 %.2f입니다.", rateOfReturn);
    }

}
