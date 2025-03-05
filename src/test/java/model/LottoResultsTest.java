package model;

import fixture.LottoNumbersFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultsTest {

    private static final int PURCHASE_AMOUNT_FOR_ONE_LOTTO = 1_000;

    private LottoNumbers winningLottoNumbers;
    private List<LottoNumbersFixture> lottoNumbersFixtures;

    @BeforeEach
    void initTestData() {
        winningLottoNumbers = LottoNumbersFixture.WINNING_LOTTO.getValue();
        lottoNumbersFixtures = LottoNumbersFixture.getFixturesExcludeWinningLotto();
    }

    @Test
    @DisplayName("로또 구매 가격 대비 총 수익률을 반환한다")
    void getTotalProfitRate() {
        for (LottoNumbersFixture lottoNumbersFixture : lottoNumbersFixtures) {
            LottoRank expectedRank = lottoNumbersFixture.getExpectedRank();
            LottoResults lottoResults = createLottoResultsFromLottoNumbersFixture(lottoNumbersFixture);

            double actualTotalProfitRate = lottoResults.getTotalProfitRate(PURCHASE_AMOUNT_FOR_ONE_LOTTO);
            double expectedTotalProfitRate = expectedRank.prizeAmount / (double) PURCHASE_AMOUNT_FOR_ONE_LOTTO;

            assertThat(actualTotalProfitRate).isEqualTo(expectedTotalProfitRate);
        }
    }

    @Test
    @DisplayName("로또 당첨 결과를 담은 컬렉션을 반환한다")
    void getLottoResultCollection() {
        for (LottoNumbersFixture lottoNumbersFixture : lottoNumbersFixtures) {
            LottoRank expectedRank = lottoNumbersFixture.getExpectedRank();
            LottoResults lottoResults = createLottoResultsFromLottoNumbersFixture(lottoNumbersFixture);

            LottoResult lottoResult = extractLottoResultByLottoRank(lottoResults, expectedRank);

            assertThat(lottoResult.getLottoAmount()).isEqualTo(1);
        }
    }

    private LottoResult extractLottoResultByLottoRank(LottoResults lottoResults, LottoRank lottoRank) {
        return lottoResults.getLottoResultCollection()
                .stream()
                .filter(lottoResult -> lottoResult.getPrizeAmount() == lottoRank.prizeAmount)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("lottoRank와 일치하는 대한 로또 결과가 없습니다."));
    }

    private LottoResults createLottoResultsFromLottoNumbersFixture(LottoNumbersFixture lottoNumbersFixture) {
        List<LottoNumbers> lottoNumbersCollection = List.of(lottoNumbersFixture.getValue());
        Lottos lottos = Lottos.purchase(PURCHASE_AMOUNT_FOR_ONE_LOTTO, lottoNumbersCollection);

        return new LottoResults(lottos, winningLottoNumbers, LottoNumbersFixture.NOT_EQUALS_BONUS_BALL);
    }

}
