import java.util.ArrayList;
import java.util.List;

public class LottoGameResult {
    private static final int LOTTO_PRICE = 1000;

    private List<Lotto> lottos;
    private WinningNumbers winningNumbers;

    public LottoGameResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        this.lottos = lottos;
        this.winningNumbers = winningNumbers;
    }

    public void printWinningStatistics() {
        List<Integer> matchCount = new ArrayList<>(7); // 로또 개수 저장
        for (int i = 0; i < 7; i++) {
            matchCount.add(0);
        }

        for (Lotto lotto : lottos) {
            int match = winningNumbers.countMatchingNumbers(lotto);
            matchCount.set(match, matchCount.get(match) + 1);
        }
        printStatistics(matchCount);
    }

    private void printStatistics(List<Integer> matchCount) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원) - " + matchCount.get(3) + "개");
        System.out.println("4개 일치 (50000원) - " + matchCount.get(4) + "개");
        System.out.println("5개 일치 (1500000원) - " + matchCount.get(5) + "개");
        System.out.println("6개 일치 (2000000000원) - " + matchCount.get(6) + "개");
        double profitRate = calculateProfitRate(matchCount);
        System.out.printf("총 수익률은 %.2f입니다.%n", profitRate);
    }

    private double calculateProfitRate(List<Integer> matchCount) {
        int totalPrize = matchCount.get(3) * 5000 + matchCount.get(4) * 50000 + matchCount.get(5) * 1500000 + matchCount.get(6) * 2000000000;
        int totalSpent = LOTTO_PRICE * lottos.size();
        return (double) totalPrize / totalSpent;
    }
}
