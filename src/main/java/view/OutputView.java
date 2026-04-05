package view;

import dto.WinningResult;

import java.util.List;

public class OutputView {

    public void printResultHeader(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printLottos(List<List<Integer>> lottoNumbers) {
        for (List<Integer> numbers : lottoNumbers) {
            System.out.println(sortLotto(numbers));
        }
    }

    public void printWinningStatistics(List<WinningResult> winningResults) {
        printStatisticsHeader();
        printRankCount(winningResults);
    }

    private List<Integer> sortLotto(List<Integer> lotto) {
        return lotto.stream()
                .sorted()
                .toList();
    }

    private void printStatisticsHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private void printRankCount(List<WinningResult> winningResults) {
        for (WinningResult winningResult : winningResults) {
            System.out.println(winningResult.message() + " - " + winningResult.count() + "개");
        }
    }

    public void printProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + String.format("%.2f", profitRate) + "입니다.");
    }
}
