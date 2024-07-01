package org.duckstudy.model.lotto.constant;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum WinningRank {

    NONE(List.of(0, 1, 2), 0, (matchCount, matchBonus) -> matchCount < 3),
    FIFTH(List.of(3), 5_000, (matchCount, matchBonus) -> matchCount == 3),
    FOURTH(List.of(4), 50_000, (matchCount, matchBonus) -> matchCount == 4),
    THIRD(List.of(5), 1_500_000, (matchCount, matchBonus) -> matchCount == 5 && !matchBonus),
    SECOND(List.of(5), 30_000_000, (matchCount, matchBonus) -> matchCount == 5 && matchBonus),
    FIRST(List.of(6), 2_000_000_000, (matchCount, matchBonus) -> matchCount == 6);

    private final List<Integer> matchCount;
    private final int price;
    private final BiPredicate<Integer, Boolean> isMatchPredicate;

    WinningRank(final List<Integer> matchCount, final int price,
                final BiPredicate<Integer, Boolean> isMatchPredicate) {
        this.matchCount = matchCount;
        this.price = price;
        this.isMatchPredicate = isMatchPredicate;
    }

    public static WinningRank findByMatchCountAndBonus(final int matchCount, final boolean matchBonus) {
        return Arrays.stream(values())
                .filter(winningLank -> winningLank.isMatch(matchCount, matchBonus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("적절한 당첨 등수를 찾을 수 없습니다."));
    }

    private boolean isMatch(final int matchCount, final boolean matchBonus) {
        return isMatchPredicate.test(matchCount, matchBonus);
    }

    public List<Integer> getMatchCount() {
        return matchCount;
    }

    public int getPrice() {
        return price;
    }
}
