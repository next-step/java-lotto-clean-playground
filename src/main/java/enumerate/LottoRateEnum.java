package enumerate;

import java.util.Arrays;

public enum LottoRateEnum {

    THREE_MATCHED(3, 5_000L),
    FOUR_MATCHED(4, 50_000L),
    FIVE_MATCHED(5, 1_500_000L),
    SIX_MATCHED(6, 2_000_000_000L),
    ;

    public final int matchCount;
    public final long price;

    LottoRateEnum(int matchCount, long price) {
        this.matchCount = matchCount;
        this.price = price;
    }

    public static LottoRateEnum getLottoRate(int matchCount) {
        return Arrays.stream(values())
            .filter(rate -> rate.matchCount == matchCount)
            .findFirst()
            .orElse(null);
    }
}
