package domain;

import java.util.List;

public class LottoCalculator {
    // Enum for prize amounts
    public enum Prize {
        MATCH_3(3, 5000),
        MATCH_4(4, 50000),
        MATCH_5(5, 1500000),
        MATCH_6(6, 2000000000),
        MATCH_5_WITH_BONUS(7, 30000000);

        private final int matchCount;
        private final int amount;

        Prize(int matchCount, int amount) {
            this.matchCount = matchCount;
            this.amount = amount;
        }

        public int getMatchCount() {
            return matchCount;
        }

        public int getAmount() {
            return amount;
        }
    }

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
        double totalPrize = 0;
        for (Prize prize : Prize.values()) {
            totalPrize += matchCounts[prize.getMatchCount()] * prize.getAmount();
        }

        double totalPrice = totalLottoCount * Lotto.PRICE_PER_TICKET;

        if (totalPrice == 0) {
            return 0;
        }

        return totalPrize / totalPrice;
    }
}