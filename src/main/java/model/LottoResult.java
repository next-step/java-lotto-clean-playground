package model;

import constants.ErrorMessageConstants;
import constants.LottoSettingsConstants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

//    public static LottoResult calculateResult(List<Integer> winningNumbers, Lotto lotto) {
//        Set<Integer> lottoNumbers= new HashSet<>(lotto.getNumbers());
//        Set<Integer> winningNumberSet = new HashSet<>(winningNumbers);
//        lottoNumbers.retainAll(winningNumberSet);
//
//        return Arrays.stream(values())
//                .filter(result->lottoNumbers.size() == result.matchCount)
//                .findFirst()
//                .orElseThrow(()->new IllegalArgumentException(ErrorMessageConstants.NO_MATCHING_RESULT));
//    }
}
