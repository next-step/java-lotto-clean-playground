package model;

import fixture.LottoNumbersFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultsTest {

    private static final int PURCHASE_AMOUNT_FOR_ONE_LOTTO = 1_000;
    private static final LottoNumber BASIC_BONUS_BALL = new LottoNumber(7);

    @Test
    @DisplayName("로또 구매 가격 대비 총 수익률을 반환한다")
    void getTotalProfitRate() {
        LottoNumbers winningLottoNumbers = LottoNumbersFixture.WINNING_LOTTO.getInstance();
        LottoNumbers sixEqualsLottoNumbers = LottoNumbersFixture.SIX_EQUALS_LOTTO.getInstance();
        Lottos lottos = Lottos.purchase(PURCHASE_AMOUNT_FOR_ONE_LOTTO, List.of(sixEqualsLottoNumbers));

        LottoResults lottoResults = new LottoResults(lottos, winningLottoNumbers, BASIC_BONUS_BALL);
        double actualTotalProfitRate = lottoResults.getTotalProfitRate(PURCHASE_AMOUNT_FOR_ONE_LOTTO);
        double expectedTotalProfitRate = LottoRank.SIX_EQUALS.prizeAmount / (double) PURCHASE_AMOUNT_FOR_ONE_LOTTO;

        assertThat(actualTotalProfitRate).isEqualTo(expectedTotalProfitRate);
    }

    @Test
    @DisplayName("로또 당첨 결과를 담은 컬렉션을 반환한다")
    void getLottoResultCollection() {
        LottoNumbers winningLottoNumbers = LottoNumbersFixture.WINNING_LOTTO.getInstance();
        LottoNumbers sixEqualsLottoNumbers = LottoNumbersFixture.SIX_EQUALS_LOTTO.getInstance();
        Lottos lottos = Lottos.purchase(PURCHASE_AMOUNT_FOR_ONE_LOTTO, List.of(sixEqualsLottoNumbers));

        LottoResults lottoResults = new LottoResults(lottos, winningLottoNumbers, BASIC_BONUS_BALL);
        List<LottoResult> lottoResultList = lottoResults.getLottoResultList();
        LottoResult sixEqualsLottoResult = extractSixEqualsLottoResult(lottoResultList);

        assertThat(sixEqualsLottoResult.getLottoAmount()).isEqualTo(1);
    }

    private LottoResult extractSixEqualsLottoResult(List<LottoResult> lottoResults) {
        return lottoResults.stream()
                .filter(lottoResult -> lottoResult.getEqualCount() == 6)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("6개 일치에 대한 로또 결과가 없습니다."));
    }

}
