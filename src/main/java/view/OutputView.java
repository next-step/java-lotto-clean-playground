package view;

import java.util.List;
import model.Rank;
import view.dto.LottoResponse;
import view.dto.LottoStatisticsResponse;

public class OutputView {

    private static final String MATCH_RESULT_FORMAT = "%d개 일치 (%d원)- %d개";
    private static final String MATCH_RESULT_2ND_PRIZE_FORMAT = "%d개 일치, 보너스 볼 일치(%d원)- %d개";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.2f입니다.";

    public void printLotto(List<LottoResponse> lottos) {
        printLinebreak();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.stream()
              .map(LottoResponse::numbers)
              .forEach(System.out::println);
    }

    private void printLinebreak() {
        System.out.print(System.lineSeparator());
    }

    public void printResultStatistics(LottoStatisticsResponse response) {
        printLinebreak();
        System.out.println("당첨 통계");
        System.out.println("---------");
        response.countOfRank().keySet().stream()
                .filter(rank -> rank != Rank._LAST_PRIZE)
                .sorted()
                .forEach(rank -> {
                    int count = response.countOfRank().get(rank);
                    if (rank == Rank._2ND_PRIZE) {
                        System.out.printf(MATCH_RESULT_2ND_PRIZE_FORMAT, rank.matchCount(), rank.prize(), count);
                        printLinebreak();
                        return;
                    }
                    System.out.printf(MATCH_RESULT_FORMAT, rank.matchCount(), rank.prize(), count);
                    printLinebreak();
                });

        System.out.printf(PROFIT_RATE_FORMAT, response.profitRate());
    }
}
