package model;

import constants.ErrorMessageConstants;
import constants.LottoSettingsConstants;

import java.util.Arrays;

public enum LottoResult {
    NO_MATCH(0, LottoSettingsConstants.NO_WIN),
    ONE(1,LottoSettingsConstants.NO_WIN),
    TWO(2,LottoSettingsConstants.NO_WIN),
    THREE(3, LottoSettingsConstants.THREE_MATCH_PRICE),
    FOUR(4, LottoSettingsConstants.FOUR_MATCH_PRICE),
    FIVE(5, LottoSettingsConstants.FIVE_MATCH_PRICE),
    SIX(6, LottoSettingsConstants.SIX_MATCH_PRICE);

    private final int matchCount;
    private final int reward;

    LottoResult(int matchCount, int reward) {
        this.matchCount = matchCount;
        this.reward= reward;
    }

    public int getReward() {
        return reward;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public static LottoResult calculateLottoResult(int i) {
        return Arrays.stream(LottoResult.values())
                .filter(result-> i == result.matchCount)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException(ErrorMessageConstants.NO_MATCHING_RESULT));
    }

}
