package org.duckstudy.model.lotto.constant;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum WinningRank {

    NONE(0, 0, 0, (matchCount, matchBonus) -> matchCount < 3),
    FIFTH(3, 5_000, 3, (matchCount, matchBonus) -> matchCount == 3),
    FOURTH(4, 50_000, 4, (matchCount, matchBonus) -> matchCount == 4),
    THIRD(5, 1_500_000, 5, (matchCount, matchBonus) -> matchCount == 5 && !matchBonus),
    SECOND(5, 30_000_000, -5, (matchCount, matchBonus) -> matchCount == 5 && matchBonus),
    FIRST(6, 2_000_000_000, 6, (matchCount, matchBonus) -> matchCount == 6);

    private final int matchCount;
    private final int price;
    private final int key;
    private final BiPredicate<Integer, Boolean> isMatchPredicate;

    WinningRank(int matchCount, int price, int key, BiPredicate<Integer, Boolean> isMatchPredicate) {
        this.matchCount = matchCount;
        this.price = price;
        this.key = key;
        this.isMatchPredicate = isMatchPredicate;
    }

    public static int findByMatchCountAndBonus(int matchCount, boolean matchBonus) {
        return Stream.of(values())
                .filter(winningLank -> winningLank.isMatch(matchCount, matchBonus))
                .findFirst()
                .map(WinningRank::getKey)
                .orElseThrow(() -> new IllegalArgumentException("적절한 당첨 등수를 찾을 수 없습니다."));
    }

    private boolean isMatch(int matchCount, boolean matchBonus) {
        return isMatchPredicate.test(matchCount, matchBonus);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrice() {
        return price;
    }

    public int getKey() {
        return key;
    }
}
