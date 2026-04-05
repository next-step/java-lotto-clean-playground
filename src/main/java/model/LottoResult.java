package model;

import constants.ErrorMessageConstants;
import constants.LottoSettingsConstants;

import java.util.Arrays;

public enum LottoResult {
    NO_MATCH(0, 0, LottoSettingsConstants.NO_WIN),
    ONE(1, 0, LottoSettingsConstants.NO_WIN),
    TWO(2, 0,LottoSettingsConstants.NO_WIN),
    THREE(3, 0, LottoSettingsConstants.THREE_MATCH_PRICE),
    FOUR(4, 0, LottoSettingsConstants.FOUR_MATCH_PRICE),
    FIVE_WITH_BONUS(5, 1, LottoSettingsConstants.FIVE_WITH_BONUS_MATCH_PRICE),
    FIVE(5, 0, LottoSettingsConstants.FIVE_MATCH_PRICE),
    SIX(6, 0, LottoSettingsConstants.SIX_MATCH_PRICE);

    private final int matchCount;
    private final int bonusMatchCount;
    private final int reward;

    LottoResult(int matchCount, int bonusMatchCount,int reward) {
        this.matchCount = matchCount;
        this.bonusMatchCount = bonusMatchCount;
        this.reward= reward;
    }

    public int getReward() {
        return reward;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public static LottoResult calculateLottoResult(int matchCount, int bonusMatchCount) {
        return Arrays.stream(LottoResult.values())
                .filter(result-> matchCount == result.matchCount && bonusMatchCount >= result.bonusMatchCount)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException(ErrorMessageConstants.NO_MATCHING_RESULT));
    }

}
