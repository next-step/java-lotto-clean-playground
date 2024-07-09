package lotto.domain;

import java.util.Arrays;

public enum LottoRate {
    THREE_MATCHED(5000, 3),
    FOUR_MATCHED(50000, 4),
    FIVE_MATCHED(1500000, 5),
    SIX_MATCHED(2000000000, 6),
    NONE(0, 0),
    ;

    private final int price;
    private final int matchCount;

    LottoRate(int price, int matchCount) {
        this.price = price;
        this.matchCount = matchCount;
    }

    public static LottoRate from(int count) {
        return Arrays.stream(values())
            .filter(it -> it.matchCount == count)
            .findAny()
            .orElse(NONE);
    }

    public int getPrice() {
        return price;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
