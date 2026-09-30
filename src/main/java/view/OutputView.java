package view;

import domain.Lotto;
import domain.Lottos;
import domain.Rank;
import dto.ResultDto;

import java.util.List;

public class OutputView {
    public void printLottos(Lottos lottos) {
        List<Lotto> lotto = lottos.getLottos();
        int count = lotto.size();
        System.out.println(count + "개를 구매했습니다.");

        for (int i = 0; i < count; i++) {
            printLotto(lotto.get(i));
        }
    }

    private void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

    public void printResult(ResultDto resultDto) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Rank rank : Rank.values()) {
            printWinningResult(rank, resultDto.results().get(rank));
        }

        printRateOfReturn(resultDto.rateOfReturn());
    }

    private void printWinningResult(Rank rank, int winningCount) {
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
