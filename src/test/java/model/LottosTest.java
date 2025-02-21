package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.RandomLottoNumbersGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static model.LottoConstraints.LOTTO_PRICE;

class LottosTest {

    private static final int BASIC_PURCHASE_AMOUNT = LOTTO_PRICE * 10;
    private static final int PURCHASE_AMOUNT_FOR_FIVE_LOTTOS = LOTTO_PRICE * 5;
    private static final int BASIC_MANUAL_LOTTO_AMOUNT = 5;
    private static final List<LottoNumbers> BASIC_MANUAL_LOTTO_NUMBERS = createBasicManualLottoNumbers();
    private static final List<LottoNumbers> EMPTY_MANUAL_LOTTO_NUMBERS = List.of();

    @Test
    @DisplayName("구매 금액과 수동 로또 리스트를 통해 인스턴스를 생성한다")
    void createByPurchaseAmountAndManualLottoNumbers() {
        Lottos lottos = Lottos.purchase(BASIC_PURCHASE_AMOUNT, BASIC_MANUAL_LOTTO_NUMBERS);
    }

    @ParameterizedTest
    @DisplayName("구매 금액이 로또 가격보다 적으면 예외가 발생한다")
    @ValueSource(ints = {Integer.MIN_VALUE, -99999, -1000, -1, 0, 999})
    void ifPurchaseAmountLessThanLottoPriceThenThrowException(int illegalPurchaseAmount) {
        Assertions.assertThatThrownBy(() -> Lottos.purchase(illegalPurchaseAmount, EMPTY_MANUAL_LOTTO_NUMBERS))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("구매 가격이 로또 가격으로 나누어 떨어지지 않으면 예외가 발생한다")
    @ValueSource(ints = {1001, 9999, 12345, 50001, Integer.MAX_VALUE})
    void ifPurchaseAmountNotDivisibleThenThrowException(int illegalPurchaseAmount) {
        Assertions.assertThatThrownBy(() -> Lottos.purchase(illegalPurchaseAmount, BASIC_MANUAL_LOTTO_NUMBERS))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("구매 가능한 로또 수보다 많은 수동 로또를 발급하려 하면 예외가 발생한다")
    @ValueSource(ints = {6, 10, 11, 100, 1000})
    void ifWantToIssueTooManyManualLottoThenThrowException(int illegalManualLottoAmount) {
        List<LottoNumbers> illegalManualLottoNumbers = createLottoNumbers(illegalManualLottoAmount);
        Assertions.assertThatThrownBy(() -> Lottos.purchase(PURCHASE_AMOUNT_FOR_FIVE_LOTTOS, illegalManualLottoNumbers));
    }

    private static List<LottoNumbers> createBasicManualLottoNumbers() {
        List<LottoNumbers> basicManualLottoNumbers = new ArrayList<>();

        for (int i = 0; i < BASIC_MANUAL_LOTTO_AMOUNT; i++) {
            basicManualLottoNumbers.add(RandomLottoNumbersGenerator.getRandomLottoNumbers());
        }

        return Collections.unmodifiableList(basicManualLottoNumbers);
    }

    private List<LottoNumbers> createLottoNumbers(int lottoAmount) {
        List<LottoNumbers> basicManualLottoNumbers = new ArrayList<>();

        for (int i = 0; i < lottoAmount; i++) {
            basicManualLottoNumbers.add(RandomLottoNumbersGenerator.getRandomLottoNumbers());
        }

        return Collections.unmodifiableList(basicManualLottoNumbers);
    }

}