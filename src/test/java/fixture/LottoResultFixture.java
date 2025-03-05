package fixture;

import model.LottoRank;
import model.LottoResult;

import java.util.Arrays;
import java.util.List;

public enum LottoResultFixture {

    THREE_EQUALS_RESULT(LottoRank.THREE_EQUALS),
    FOUR_EQUALS_RESULT(LottoRank.FOUR_EQUALS),
    FIVE_EQUALS_RESULT(LottoRank.FIVE_EQUALS),
    FIVE_EQUALS_WITH_BONUS_BALL_RESULT(LottoRank.FIVE_WITH_BONUS_EQUALS),
    SIX_EQUALS_RESULT(LottoRank.SIX_EQUALS);

    private final LottoRank lottoRank;

    LottoResultFixture(LottoRank lottoRank) {
        this.lottoRank = lottoRank;
    }

    public LottoResult getValue() {
        return new LottoResult(lottoRank);
    }

    public static List<LottoResult> getEveryInstance() {
        return Arrays.stream(values())
                .map(LottoResultFixture::getValue)
                .toList();
    }

}
