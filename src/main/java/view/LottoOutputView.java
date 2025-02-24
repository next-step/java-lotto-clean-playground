package view;

import dto.LottoRankResultDTO;

import java.util.Comparator;
import java.util.List;

public class LottoOutputView {

    public void printRequestAmount() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printRequestPassivityLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public void printRequestPassivityLottoNumbers() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public void printBuyLottos(long passivityCount, long automaticCount, List<List<Integer>> lottosNumbers) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", passivityCount, automaticCount);
        for (List<Integer> lottoNumbers : lottosNumbers) {
            System.out.println(lottoNumbers);
        }
    }

    public void printRequestLastWeekWinLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printRequestBonusLottoNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public void printLottoResult(List<LottoRankResultDTO> lottoResults) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        lottoResults.stream()
                .filter(result -> result.matchedCount() >= 3)
                .sorted(Comparator.comparing(LottoRankResultDTO::prize))
                .forEach(this::printLottoRankResult
                );
    }

    private void printLottoRankResult(LottoRankResultDTO result) {
        System.out.printf("%d개 일치", result.matchedCount());
        if (result.isBonusNumber()) {
            System.out.print(", 보너스 볼 일치");
        }
        System.out.printf(" (%d원)- %d개\n", result.prize(), result.resultCount());
    }

    public void printEarningsRate(double earningsRate) {
        System.out.printf("총 수익률은 %.2f입니다.\n", Math.floor(earningsRate * 100) / 100);
    }
}
