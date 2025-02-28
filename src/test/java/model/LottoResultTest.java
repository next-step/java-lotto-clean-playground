package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    private static final LottoRank BASIC_LOTTO_RANK = LottoRank.THREE_EQUALS;
    private static final int BASIC_REPEAT_COUNT = 100;
    private static final Comparator<LottoResult> NATURAL_COMPARATOR = null;

    @Test
    @DisplayName("LottoRank를 통해 인스턴스를 생성한다")
    void createByLottoRank() {
        for (LottoRank lottoRank : LottoRank.values()) {
            LottoResult lottoResult = new LottoResult(lottoRank);
        }
    }

    @Test
    @DisplayName("lottoAmount를 1 증가시킨다")
    void increaseLottoAmount() {
        LottoResult lottoResult = new LottoResult(BASIC_LOTTO_RANK);
        int expectedLottoAmount = 0;

        for (int i = 0; i < BASIC_REPEAT_COUNT; i++) {
            int actualLottoAmount = lottoResult.getLottoAmount();
            assertThat(actualLottoAmount).isEqualTo(expectedLottoAmount);

            lottoResult.increaseLottoAmount();
            expectedLottoAmount++;
        }
    }

    @Test
    @DisplayName("내부적으로 보유한 LottoRank를 기준으로 정렬된다")
    void sortedByLottoRank() {
        List<LottoResult> lottoResults = getEveryLottoResults();
        lottoResults.sort(NATURAL_COMPARATOR);

        for (int i = 0; i < lottoResults.size(); i++) {
            LottoResult lottoResult = lottoResults.get(i);
            LottoRank lottoRank = LottoRank.values()[i];

            assertThat(lottoResult.getPrizeAmount()).isEqualTo(lottoRank.prizeAmount);
        }
    }

    private List<LottoResult> getEveryLottoResults() {
        List<LottoResult> lottoResults = new ArrayList<>();

        for (LottoRank lottoRank : LottoRank.values()) {
            lottoResults.add(new LottoResult(lottoRank));
        }

        return lottoResults;
    }

}