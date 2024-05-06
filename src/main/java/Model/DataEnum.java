package Model;

import View.InputData;

public class DataEnum {
    public enum LottoResult {
        THREE_MATCHES(3, false, 5000),
        FOUR_MATCHES(4, false, 50000),
        FIVE_MATCHES(5, false, 1500000),
        FIVE_MATCHES_BONUS(5, true, 30000000),
        SIX_MATCHES(6, false, 2000000000);

        private final int matches;
        private final boolean bonus;
        private final int reward;

        LottoResult(int matches, boolean bonus, int reward) {
            this.matches = matches;
            this.bonus = bonus;
            this.reward = reward;
        }

        public int getMatches() {
            return matches;
        }

        public boolean isBonus() {
            return bonus;
        }

        public int getReward() {
            return reward;
        }

        public static void applyResult(int[] matchList, int matchCount, boolean bonusResult) {
            for (LottoResult result : values()) {
                isRight(matchList, matchCount, bonusResult, result);
            }
        }

        private static void isRight(int[] matchList, int matchCount, boolean bonusResult, LottoResult result) {
            if (matchCount == 5 && result.bonus == bonusResult) {
                int index = result.ordinal();
                matchList[index]++;
                InputData.profit += result.reward;
            }
            else if (result.matches == matchCount){
                int index = result.ordinal();
                matchList[index]++;
                InputData.profit += result.reward;
            }
        }
    }

}
