package view;

import dto.LottoResultDto;
import java.util.List;

public class ResultView {

    public void displayPurchasedLottoTickets(List<String> tickets, int manualCount) {
        int automaticCount = tickets.size() - manualCount;
        System.out.println("수동으로 " + manualCount + "장, 자동으로 " + automaticCount + "개를 구매했습니다.");
        for (String ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public void printLottoStatistics(List<LottoResultDto> lottoResultDto) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (LottoResultDto result : lottoResultDto) {;
            if (result.isBonusMatch()) {
                System.out.printf("%d개 일치, 보너스 볼 일치 (%d원) - %d개%n",
                        result.getMatchCount(), result.getPrice(), result.getCount());
            }
            System.out.printf("%d개 일치 (%d원) - %d개%n",
                    result.getMatchCount(), result.getPrice(), result.getCount());
        }
    }

    public void printEarningsRate(double earningsRate) {
        String result = "손해";
        if (earningsRate > 1) {
            result = "이익";
        }

        System.out.println("총 수익률은 " + String.format("%.2f", earningsRate) +
                "입니다.(기준이 1이기 때문에 결과적으로 " + result + "라는 의미임)");
    }
}
