package org.duckstudy.model.lotto.constant;

import java.util.stream.Stream;

public enum WinningRank {

    NONE(0, 0, 0),
    FIFTH(3, 5_000, 3),
    FOURTH(4, 50_000, 4),
    THIRD(5, 1_500_000, 5),
    SECOND(5, 30_000_000, -5),
    FIRST(6, 2_000_000_000, 6);

    private final int matchCount;
    private final int price;
    private final int key;

    WinningRank(int matchCount, int price, int key) {
        this.matchCount = matchCount;
        this.price = price;
        this.key = key;
    }

    public static WinningRank findByMatchCountAndBonus(int matchCount, boolean matchBonus) {
        return Stream.of(values())
                .filter(winningLank -> winningLank.isMatch(matchCount, matchBonus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("적절한 당첨 등수를 찾을 수 없습니다."));
    }

    private boolean isMatch(int matchCount, boolean matchBonus) {
        if (this == SECOND) {
            return matchCount == 5 && matchBonus;
        }
        if (this == THIRD) {
            return matchCount == 5 && !matchBonus;
        }
        return this.matchCount == matchCount;
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
