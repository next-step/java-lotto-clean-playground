package model;

import constants.LottoSettingsConstants;

public enum LottoResult {
    NO_MATCH(0, LottoSettingsConstants.NO_WIN),
    ONE(1,LottoSettingsConstants.NO_WIN),
    TWO(2,LottoSettingsConstants.NO_WIN),
    THREE(3, LottoSettingsConstants.THREE_MATCH_PRICE),
    FOUR(4, LottoSettingsConstants.FOUR_MATCH_PRICE),
    FIVE(5, LottoSettingsConstants.FIVE_MATCH_PRICE),
    SIX(6, LottoSettingsConstants.SIX_MATCH_PRICE);

    public final int matchCount;
    public final int reward;

    LottoResult(int matchCount, int reward) {
        this.matchCount = matchCount;
        this.reward= reward;
    }
}
