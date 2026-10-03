package view;

import domain.Lotto;
import domain.Lottos;
import domain.PurchaseCount;
import domain.Rank;
import dto.ResultDto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class OutputView {
    public void printLottos(Lottos lottos, PurchaseCount purchaseCount) {
        List<Lotto> lotto = lottos.getLottos();

        System.out.println(
                "\n수동으로 " + purchaseCount.getManualCount() + "장, "
                + "자동으로 " + purchaseCount.getAutoCount() + "개를 구매했습니다."
        );

        for (int i = 0; i < lotto.size(); i++) {
            printLotto(lotto.get(i));
        }
    }

    private void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

    public void printResult(ResultDto resultDto) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        printWinningResults(resultDto);

        printRateOfReturn(resultDto.rateOfReturn());
    }

    private void printWinningResults(ResultDto resultDto) {
        Rank.winningRanks().stream()
              .sorted(Comparator.comparingInt(Rank::getOrder))
              .forEach(rank -> printWinningResult(
                     rank,
                     resultDto.results().get(rank)
             ));
    }

    private void printWinningResult(Rank rank, int winningCount) {
        if (rank.isBonusRequired()) {
            printBonusWinningResult(rank, winningCount);
            return;
        }
        printNormalWinningResult(rank, winningCount);
    }

    private void printBonusWinningResult(Rank rank, int winningCount) {
        System.out.println(
                rank.getMatchCount() + "개 일치, 보너스 볼 일치("
                        + rank.getPrize() + "원)- "
                        + winningCount + "개"
        );
    }

    private void printNormalWinningResult(Rank rank, int winningCount) {
        System.out.println(
                rank.getMatchCount() + "개 일치 ("
                        + rank.getPrize() + "원)- "
                        + winningCount + "개"
        );
    }

    public void printError(String message) {
        System.out.println(message);
    }

    private void printRateOfReturn(double rateOfReturn) {
        double truncatedRateOfReturn = Math.floor(rateOfReturn * 100) / 100;

        System.out.println("총 수익률은 " + truncatedRateOfReturn + "입니다");
    }
}
