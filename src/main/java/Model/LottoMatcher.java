package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoMatcher {
    private final Map<Integer, Integer> matchCounts;

    public LottoMatcher(Lottos lottos, Jackpot jackpot) {
        this.matchCounts = new HashMap<>();

        for (Lotto lotto : lottos) {
            int matches = countMatches(jackpot.getJackpot(), lotto.getLotto());
            checkBonus(jackpot, lotto, matches);
        }

    }

    private void checkBonus(Jackpot jackpot, Lotto lotto, int matches) {
        if (matches == 5 && lotto.getLotto().contains(jackpot.getBounsNumber())) {
            matchCounts.put(7, matchCounts.getOrDefault(7, 0) + 1);
            return;
        }
        matchCounts.put(matches, matchCounts.getOrDefault(matches, 0) + 1);
    }

    private int countMatches(List<Integer> jackpotNumbers, List<Integer> lottoNumbers) {
        int count = 0;
        for (int number : lottoNumbers) {
            count += getMatchValue(jackpotNumbers, number);
        }
        return count;
    }


    private int getMatchValue(List<Integer> jackpotNumbers, int number) {
        if (jackpotNumbers.contains(number)) {
            return 1;
        }
        return 0;
    }

    public Map<Integer, Integer> getMatchCounts() {
        return matchCounts;
    }

    public double calculateTotalEarnings() {

        return    matchCounts.getOrDefault(3, 0) * PrizeMoney.THREE.getMoney()
                + matchCounts.getOrDefault(4, 0) * PrizeMoney.FOUR.getMoney()
                + matchCounts.getOrDefault(5, 0) * PrizeMoney.FIVE.getMoney()
                + matchCounts.getOrDefault(7, 0) * PrizeMoney.BONUS.getMoney()
                + matchCounts.getOrDefault(6, 0) * PrizeMoney.SIX.getMoney();
    }

    public double getRate(int money) {
        return calculateTotalEarnings() / money;
    }

}
