package model;

import constants.ErrorMessageConstants;
import constants.LottoSettingsConstants;

import java.util.Arrays;

public enum LottoResult {
    NO_MATCH(0, 0, 0),
    ONE(1, 0, 0),
    TWO(2, 0,0),
    THREE(3, 0, 5000),
    FOUR(4, 0, 50000),
    FIVE_WITH_BONUS(5, 1, 1500000),
    FIVE(5, 0, 30000000),
    SIX(6, 0, 2000000000);

    private final int matchCount;
    private final int bonusMatchCount;
    private final int reward;

    LottoResult(int matchCount, int bonusMatchCount,int reward) {
        this.matchCount = matchCount;
        this.bonusMatchCount = bonusMatchCount;
        this.reward= reward;
    }

    public static LottoResult calculateLottoResult(int matchCount, int bonusMatchCount) {
        return Arrays.stream(LottoResult.values())
                .filter(result-> matchCount == result.matchCount && bonusMatchCount >= result.bonusMatchCount)
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
