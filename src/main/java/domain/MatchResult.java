package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum MatchResult {
    THREE(3, 5_000, false, "3개 일치"),
    FOUR(4, 50_000, false, "4개 일치"),
    FIVE(5, 1_500_000, false, "5개 일치"),
    FIVE_BONUS(5, 30_000_000, true, "5개 일치, 보너스 볼 일치"),
    SIX(6, 2_000_000_000, false, "6개 일치"),
    MISS(0, 0, false, "");

    private final int matchCount;
    private final int matchReward;
    private final boolean bonusMatch;
    private final String label;

    MatchResult(final int matchCount, final int matchReward, boolean bonusMatch, String label) {
        this.matchCount = matchCount;
        this.matchReward = matchReward;
        this.bonusMatch = bonusMatch;
        this.label = label;
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

    public String getLabel() {
        return label;
    }
}