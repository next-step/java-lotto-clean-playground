package view;

import dto.LottoRankResultDTO;

import java.util.Comparator;
import java.util.List;

public class LottoOutputView {

    public void printRequestAmount() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printBuyLottos(long lottoCount, List<List<Integer>> lottosNumbers) {
        System.out.println(lottoCount + "개를 구매했습니다.");
        for (List<Integer> lottoNumbers : lottosNumbers) {
            System.out.println(lottoNumbers);
        }
    }

    public void printRequestLastWeekWinLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printLottoResult(List<LottoRankResultDTO> lottoResults) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        lottoResults.stream()
                .filter(result -> result.matchedCount() >= 3)
                .sorted(Comparator.comparing(LottoRankResultDTO::matchedCount))
                .forEach(result ->
                        System.out.printf("%d개 일치 (%d원)- %d개\n", result.matchedCount(), result.prize(), result.resultCount())
                );
    }

    public void printEarningsRate(double earningsRate) {
        System.out.printf("총 수익률은 %.2f입니다.\n",  Math.floor(earningsRate * 100) / 100);
    }
}
