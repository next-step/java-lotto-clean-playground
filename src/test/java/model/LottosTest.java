package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static model.LottoConstraints.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottosTest {

    private static final int PURCHASE_AMOUNT_FOR_FIVE_LOTTOS = LOTTO_PRICE * 5;
    private static final int BASIC_MANUAL_LOTTO_AMOUNT = 5;
    private static final List<LottoNumbers> BASIC_MANUAL_LOTTO_NUMBERS = createBasicManualLottoNumbers();
    private static final List<LottoNumbers> EMPTY_MANUAL_LOTTO_NUMBERS = List.of();

    private static List<LottoNumbers> createBasicManualLottoNumbers() {
        List<LottoNumbers> basicManualLottoNumbers = new ArrayList<>();

        for (int i = 0; i < BASIC_MANUAL_LOTTO_AMOUNT; i++) {
            basicManualLottoNumbers.add(RandomLottoNumbersGenerator.getRandomLottoNumbers());
        }

        return Collections.unmodifiableList(basicManualLottoNumbers);
    }

    @ParameterizedTest
    @DisplayName("구매 금액이 로또 가격보다 적으면 예외가 발생한다")
    @ValueSource(ints = {Integer.MIN_VALUE, -99999, -1000, -1, 0, 999})
    void ifPurchaseAmountLessThanLottoPriceThenThrowException(int illegalPurchaseAmount) {
        assertThatThrownBy(() -> Lottos.purchase(illegalPurchaseAmount, EMPTY_MANUAL_LOTTO_NUMBERS))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("구매 가격이 로또 가격으로 나누어 떨어지지 않으면 예외가 발생한다")
    @ValueSource(ints = {1001, 9999, 12345, 50001, Integer.MAX_VALUE})
    void ifPurchaseAmountNotDivisibleThenThrowException(int illegalPurchaseAmount) {
        assertThatThrownBy(() -> Lottos.purchase(illegalPurchaseAmount, BASIC_MANUAL_LOTTO_NUMBERS))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("구매 가능한 로또 수보다 많은 수동 로또를 발급하려 하면 예외가 발생한다")
    @ValueSource(ints = {6, 10, 11, 100, 1000})
    void ifWantToIssueTooManyManualLottoThenThrowException(int illegalManualLottoAmount) {
        List<LottoNumbers> illegalManualLottoNumbers = createRandomLottoNumbersCollection(illegalManualLottoAmount);
        assertThatThrownBy(() -> Lottos.purchase(PURCHASE_AMOUNT_FOR_FIVE_LOTTOS, illegalManualLottoNumbers));
    }

    @ParameterizedTest
    @DisplayName("로또의 개수를 반환한다")
    @ValueSource(ints = {1, 10, 100, 1000, 10000})
    void getLottoAmount(int lottoAmount) {
        Lottos lottos = createLottosFromLottoAmount(lottoAmount);

        int actualLottoAmount = lottos.getLottoAmount();

        assertThat(actualLottoAmount).isEqualTo(lottoAmount);
    }

    private List<LottoNumbers> createRandomLottoNumbersCollection(int lottoAmount) {
        List<LottoNumbers> randomLottoNumbersCollection = new ArrayList<>();

        for (int i = 0; i < lottoAmount; i++) {
            randomLottoNumbersCollection.add(RandomLottoNumbersGenerator.getRandomLottoNumbers());
        }

        return Collections.unmodifiableList(randomLottoNumbersCollection);
    }

    private Lottos createLottosFromLottoAmount(int lottoAmount) {
        int purchaseAmount = lottoAmount * LOTTO_PRICE;
        return Lottos.purchase(purchaseAmount, EMPTY_MANUAL_LOTTO_NUMBERS);
    }

}
