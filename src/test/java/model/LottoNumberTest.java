package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static model.LottoConstraints.MAXIMUM_LOTTO_NUMBER;
import static model.LottoConstraints.MINIMUM_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @Test
    @DisplayName("적정 범위 내의 숫자를 통해 인스턴스를 생성한다")
    void createLottoNumberByInteger() {
        for (Integer legalNumber : getLegalNumbers()) {
            LottoNumber lottoNumber = new LottoNumber(legalNumber);
        }
    }

    @ParameterizedTest
    @DisplayName("최소치보다 작은 숫자를 전달시 예외가 발생한다")
    @ValueSource(ints = {Integer.MIN_VALUE, -1000, -100, -10, -1, 0})
    void ifLessThanMinimumThenThrowException(int illegalNumber) {
        assertThatThrownBy(() -> new LottoNumber(illegalNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("최대치보다 높은 숫자를 전달시 예외가 발생한다")
    @ValueSource(ints = {46, 100, 999, 15000, Integer.MAX_VALUE})
    void ifBiggerThanMaximumThenThrowException(int illegalNumber) {
        assertThatThrownBy(() -> new LottoNumber(illegalNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private List<Integer> getLegalNumbers() {
        List<Integer> legalNumbers = new ArrayList<>();

        for (int legalNumber = MINIMUM_LOTTO_NUMBER; legalNumber <= MAXIMUM_LOTTO_NUMBER; legalNumber++) {
            legalNumbers.add(legalNumber);
        }

        return Collections.unmodifiableList(legalNumbers);
    }

}