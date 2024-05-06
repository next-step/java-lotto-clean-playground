package domain;

import java.util.List;

public class LottoCalculator {
    public int[] calculateWinningStatistics(List<Lotto> lottos, WinningNumbers winningNumbers, int bonusBall) {
        int[] matchCounts = new int[8];
        for (Lotto lotto : lottos) {
            int matchCount = calculateMatchCount(lotto.getLottoNumbers(), winningNumbers.getNumbers());
            boolean hasBonusBall = lotto.getLottoNumbers().contains(bonusBall);
            if (matchCount == 6) {
                matchCounts[6] += 1; // 6개 일치
            } else if (matchCount == 5 && hasBonusBall) {
                matchCounts[7] += 1; // 5개 일치 + 보너스 볼 일치
            } else {
                matchCounts[matchCount] += 1; // 그 외 일치 개수 기록
            }
        }
        return matchCounts;
    }

    private int calculateMatchCount(List<Integer> numbers, List<Integer> winningNumbers) {
        int matchCount = 0;
        for (Integer number : numbers) {
            if (winningNumbers.contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    public static double calculateProfitRate(int[] matchCounts, int totalLottoCount) {
        double totalPrize = matchCounts[3] * 5000 +
                matchCounts[4] * 50000 +
                matchCounts[5] * 1500000 +
                matchCounts[6] * 2000000000 +
                matchCounts[7] * 30000000; // 5개 일치, 보너스 볼 일치(30000000원)

        double totalPrice = totalLottoCount * Lotto.PRICE_PER_TICKET;

        if (totalPrice == 0) {
            return 0;
        }

        return (totalPrize - totalPrice) / totalPrice;
    }
}
