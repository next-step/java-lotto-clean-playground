package enumerate;

import java.util.Arrays;

public enum LottoRateEnum {
    THREE_MATCHED(3, 5_000L, Format.DEFAULT),
    FOUR_MATCHED(4, 50_000L, Format.DEFAULT),
    FIVE_MATCHED(5, 1_500_000L, Format.DEFAULT),
    FIVE_MATCHED_WITH_BONUS(5, 3_000_000L, Format.BONUS),
    SIX_MATCHED(6, 2_000_000_000L, Format.DEFAULT);

    public final int matchCount;
    public final long price;
    private final String format;

    LottoRateEnum(int matchCount, long price, String format) {
        this.matchCount = matchCount;
        this.price = price;
        this.format = format;
    }

    public static LottoRateEnum getLottoRate(int matchCount, boolean isBonusMatched) {
        if (isBonusMatched && matchCount == FIVE_MATCHED.matchCount) {
            return FIVE_MATCHED_WITH_BONUS;
        }
        return Arrays.stream(values())
            .filter(rate -> rate.matchCount == matchCount)
            .findFirst()
            .orElse(null);
    }

    public String getFormattedRank(int count) {
        return String.format(format, matchCount, price, count);
    }

    private static class Format {
        private static final String DEFAULT = "%d개 일치 (%d원) - %d개";
        private static final String BONUS = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    }
}
