package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindWinningLotto {

    public enum LottoRank {
        NONE(0, 0),
        THREE(3, 5000),
        FOUR(4, 50000),
        FIVE(5, 1500000),
        FIVE_WITH_BONUS(5, 30000000),
        SIX(6, 2000000000);

        private final int matchCount;
        private final int prize;

        LottoRank(int matchCount, int prize) {
            this.matchCount = matchCount;
            this.prize = prize;
        }

        public int getMatchCount() {
            return matchCount;
        }

        public int getPrize() {
            return prize;
        }

        public static LottoRank valueOf(int matchCount, boolean bonusMatch) {
            if (matchCount == 5 && bonusMatch) {
                return FIVE_WITH_BONUS;
            }
            for (LottoRank rank : values()) {
                if (rank.matchCount == matchCount) {
                    return rank;
                }
            }
            return NONE;
        }
    }

    public Map<LottoRank, Integer> calculateWinningResult(List<List<Integer>> manualLottoInputs, List<Integer> lastWeekWinningNumbers, int bonusWinningNumber) {
        Map<LottoRank, Integer> winningStatistics = new HashMap<>();

        for (LottoRank rank : LottoRank.values()) {
            winningStatistics.put(rank, 0);
        }

        for (List<Integer> lotto : manualLottoInputs) {
            int matchCount = (int) lotto.stream()
                    .filter(lastWeekWinningNumbers::contains)
                    .count();

            boolean bonusMatch = lotto.contains(bonusWinningNumber);

            LottoRank rank = LottoRank.valueOf(matchCount, bonusMatch);

            winningStatistics.put(rank, winningStatistics.get(rank) + 1);
        }

        return winningStatistics;
    }
}
