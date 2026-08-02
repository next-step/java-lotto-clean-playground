package view;

import domain.LottoTickets;
import domain.LottoWinningType;
import java.util.Map;

public final class OutputView {
    private OutputView() {

    }

    public static void printLottoCount(int userSelectedCount, int autoCount) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", userSelectedCount, autoCount);
    }

    public static void printLottoNumbers(LottoTickets lottoTickets) {
        for(int i = 0; i< lottoTickets.getSize(); i++) {
            System.out.println(lottoTickets.getLottoTreeSet(i));
        }
    }

    public static void printMatchCount(Map<LottoWinningType, Integer> matchStatistics) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        LottoWinningType[] printOrder = {
                LottoWinningType.FIFTH_PLACE,
                LottoWinningType.FOURTH_PLACE,
                LottoWinningType.THIRD_PLACE,
                LottoWinningType.SECOND_PLACE,
                LottoWinningType.FIRST_PLACE
        };

        for (LottoWinningType type : printOrder) {
            System.out.println(type.getWinningDescription() + matchStatistics.get(type) + "개");
        }
    }

    public static void printRateOfReturn(double rateOfReturn) {
        if (rateOfReturn < 1) {
            System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n", rateOfReturn);
        }
        if (rateOfReturn >= 1) {
            System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 이득이라는 의미임)\n", rateOfReturn);
        }
    }

}
