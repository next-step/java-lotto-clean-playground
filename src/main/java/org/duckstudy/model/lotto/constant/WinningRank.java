package org.duckstudy.model.lotto.constant;

import java.util.stream.Stream;

public enum WinningRank {

    NONE(0, 0, 0),
    FIFTH(3, 5000, 3),
    FOURTH(4, 50000, 4),
    THIRD(5, 1500000, 5),
    SECOND(5, 30000000, -5),
    FIRST(6, 2000000000, 6);

    private final int matchCount;
    private final int price;
    private final int key;

    WinningRank(int matchCount, int price, int key) {
        this.matchCount = matchCount;
        this.price = price;
        this.key = key;
    }

    public static WinningRank findByMatchCountAndBonus(int matchCount, boolean matchBonus) {
        if (matchCount == SECOND.getMatchCount() && matchBonus) {
            return SECOND;
        }
        return Stream.of(values())
                .filter(winningLank -> winningLank.getMatchCount() == matchCount)
                .findFirst()
                .orElse(NONE);
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
