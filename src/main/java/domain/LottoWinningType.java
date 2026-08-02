package domain;

import java.util.function.Function;

public enum LottoWinningTypePrize {
    PRIZE_3_MATCH(originMatchCount -> originMatchCount * 5000),
    PRIZE_4_MATCH(originMatchCount -> originMatchCount * 50000),
    PRIZE_5_MATCH(originMatchCount -> originMatchCount * 1500000),
    PRIZE_5_MATCH_AND_1_BONUS_BALL_MATCH(originMatchCount -> originMatchCount * 30000000),
    PRIZE_6_MATCH(originMatchCount -> originMatchCount * 2000000000);

    private final Function<Long, Long> expression;

    LottoWinningTypePrize(Function<Long, Long> expression) {
        this.expression = expression;
    }

    public long calculatePrize(int originMatchCount) {
        return expression.apply(originMatchCount);
    }




}
