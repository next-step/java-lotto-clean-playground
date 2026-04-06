package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum MatchResult {
    THREE(3, 5_000),
    FOUR(4, 50_000),
    FIVE(5, 1_500_000),
    SIX(6, 2_000_000_000),
    MISS(0, 0);

    private final int matchCount;
    private final int matchReward;

    MatchResult(final int matchCount, final int matchReward) {
        this.matchCount = matchCount;
        this.matchReward = matchReward;
    }

    public static Map<MatchResult, Integer> of(Lottos lottos, Lotto winningLotto) {
        Map<MatchResult, Integer> resultMap = new HashMap<>();
        for (MatchResult result : values()) {
            resultMap.put(result, 0);
        }
        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = lotto.countMatch(winningLotto);
            Arrays.stream(values())
                    .filter(r -> r.matchCount == matchCount)
                    .findFirst()
                    .ifPresent(r -> resultMap.put(r, resultMap.get(r) + 1));
        }
        return resultMap;
    }

    public static double getProfitRate(Map<MatchResult, Integer> resultMap, int purchaseAmount) {
        double totalReward = resultMap.entrySet().stream()
                .mapToDouble(e -> (double) e.getKey().matchReward * e.getValue())
                .sum();
        return totalReward / purchaseAmount;
    }

    public int getMatchCount() { return matchCount; }
    public int getMatchReward() { return matchReward; }
}