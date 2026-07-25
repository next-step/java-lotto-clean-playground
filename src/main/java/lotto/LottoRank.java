package lotto;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6),
    SECOND(5),
    THIRD(4),
    FOURTH(3),
    MISS(0);

    private final int matchCount;

    LottoRank(int matchCount) {
        this.matchCount = matchCount;
    }

    public static LottoRank from(int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(MISS);
    }
}
