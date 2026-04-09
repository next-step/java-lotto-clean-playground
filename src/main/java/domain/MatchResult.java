package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum MatchResult {
    THREE(3, 5_000, false),
    FOUR(4, 50_000, false),
    FIVE(5, 1_500_000, false),
    FIVE_BONUS(5, 30_000_000, true),
    SIX(6, 2_000_000_000, false),
    MISS(0, 0, false);

    private final int matchCount;
    private final int matchReward;
    private final boolean bonusMatch;

    MatchResult(final int matchCount, final int matchReward, boolean bonusMatch) {
        this.matchCount = matchCount;
        this.matchReward = matchReward;
        this.bonusMatch = bonusMatch;
    }

    public static Map<MatchResult, Integer> of(Lottos lottos, WinningLotto winningLotto) {
        Map<MatchResult, Integer> resultMap = new HashMap<>();
        for (MatchResult result : values()) {
            resultMap.put(result, 0);
        }
        for (Lotto lotto : lottos.getLottos()) {
           MatchResult result = winningLotto.match(lotto);
           resultMap.put(result, resultMap.get(result) + 1);
        }
        return resultMap;
    }

    public static MatchResult of(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(r -> r.matchCount == matchCount && r.bonusMatch == bonusMatch)
                .findFirst()
                .orElse(MISS);
    }

    public static double getProfitRate(Map<MatchResult, Integer> resultMap, int purchaseAmount) {
        double totalReward = resultMap.entrySet().stream()
                .mapToDouble(e -> (double) e.getKey().matchReward * e.getValue())
                .sum();
        return totalReward / purchaseAmount;
    }

    public int getMatchCount() { return matchCount; }
    public int getMatchReward() { return matchReward; }

    public boolean isBonusMatch() {
        return bonusMatch;
    }
}