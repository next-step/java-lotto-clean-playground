package model;

import constants.ErrorMessageConstants;

import java.util.Arrays;

public enum LottoResult {
    NO_MATCH(0, false, 0),
    ONE(1, false, 0),
    TWO(2, false,0),
    THREE(3, false, 5000),
    FOUR(4, false, 50000),
    FIVE(5, false, 30000000),
    FIVE_WITH_BONUS(5, true, 1500000),
    SIX(6, false, 2000000000);

    private final int matchCount;
    private final boolean bonusMatchExist;
    private final int reward;

    LottoResult(int matchCount, boolean bonusMatchExist, int reward) {
        this.matchCount = matchCount;
        this.bonusMatchExist = bonusMatchExist;
        this.reward= reward;
    }

    public static LottoResult calculateLottoResult(int matchCount, boolean bonusMatchExist) {
        if (bonusMatchExist && matchCount == FIVE.matchCount) {
            return FIVE_WITH_BONUS;
        }

        return Arrays.stream(LottoResult.values())
                .filter(result-> matchCount == result.matchCount)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException(ErrorMessageConstants.NO_MATCHING_RESULT));
    }

    public int getReward() {
        return reward;
    }

    public int getMatchCount() {
        return matchCount;
    }

}
