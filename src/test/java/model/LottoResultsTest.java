package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultsTest {

    private static final int BASIC_PURCHASE_AMOUNT = 10_000;
    private static final Lottos BASIC_LOTTOS = createBasicLottos();
    private static final LottoNumbers BASIC_WINNING_LOTTO_NUMBERS = createLottoNumbers(1, 2, 3, 4, 5, 6);
    private static final LottoNumber BASIC_BONUS_BALL = new LottoNumber(7);

    @Test
    @DisplayName("Lottos와 1등 로또, 보너스볼을 통해 로또 결과를 계산한 인스턴스를 생성한다")
    void createByLottosAndWinningLottoNumbersAndBonusBall() {
        LottoResults lottoResults = new LottoResults(BASIC_LOTTOS, BASIC_WINNING_LOTTO_NUMBERS, BASIC_BONUS_BALL);
    }

    @Test
    @DisplayName("로또 구매 가격 대비 총 수익률을 반환한다")
    void getTotalProfitRate() {
        int purchaseAmount = 1_000;
        LottoNumbers manualLottoNumbers = createLottoNumbers(1, 2, 3, 4, 5, 6);
        LottoNumbers winningLottoNumbers = createLottoNumbers(1, 2, 3, 4, 5, 6);
        Lottos lottos = Lottos.purchase(purchaseAmount, List.of(manualLottoNumbers));

        LottoResults lottoResults = new LottoResults(lottos, winningLottoNumbers, BASIC_BONUS_BALL);
        double actualTotalProfitRate = lottoResults.getTotalProfitRate(purchaseAmount);
        double expectedTotalProfitRate = LottoRank.SIX_EQUALS.prizeAmount / (double) purchaseAmount;

        assertThat(actualTotalProfitRate).isEqualTo(expectedTotalProfitRate);
    }

    private static Lottos createBasicLottos() {
        return Lottos.purchase(BASIC_PURCHASE_AMOUNT, List.of());
    }

    private static LottoNumbers createLottoNumbers(int... numbers) {
        Set<LottoNumber> lottoNumbers = new HashSet<>();

        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }

        return new LottoNumbers(lottoNumbers);
    }

}