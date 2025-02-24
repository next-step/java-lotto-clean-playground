package lotto.domain;

import java.util.Arrays;

public enum LottoRate {
    THREE_MATCHED(5000, 3, LottoRate::defaultViewMessage),
    FOUR_MATCHED(50000, 4, LottoRate::defaultViewMessage),
    FIVE_MATCHED(1500000, 5, LottoRate::defaultViewMessage),
    FIVE_MATCHED_WITH_BONUS(30000000, 5, LottoRate::bonusViewMessage),
    SIX_MATCHED(2000000000, 6, LottoRate::defaultViewMessage),
    NONE(0, 0, LottoRate::defaultViewMessage);

    private final int price;
    private final int matchCount;
    private final ViewMessageFormatter viewMessage;

    LottoRate(int price, int matchCount, ViewMessageFormatter viewMessage) {
        this.price = price;
        this.matchCount = matchCount;
        this.viewMessage = viewMessage;
    }

    public static LottoRate from(AnswerMatchModel match) {
        if (match.isBonus() && match.count() == 5) {
            return FIVE_MATCHED_WITH_BONUS;
        }
        return Arrays.stream(values())
            .filter(it -> it.matchCount == match.count())
            .findAny()
            .orElse(NONE);
    }

    public int getPrice() {
        return price;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public String getViewMessage(int count) {
        return viewMessage.format(this, count);
    }

    private static String defaultViewMessage(LottoRate rate, int count) {
        return String.format("%d개 일치 (%d)- %d개", rate.matchCount, rate.price, count);
    }

    private static String bonusViewMessage(LottoRate rate, int count) {
        return String.format("%d개 일치, 보너스 볼 일치(%d)- %d개", rate.matchCount, rate.price, count);
    }

    @FunctionalInterface
    public interface ViewMessageFormatter {
        String format(LottoRate rate, int count);
    }
}
