package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("로또 번호가 1보다 작거나 45보다 크면 예외가 발생해야 한다")
    void testLottoNumberOutOfRangeException(int number) {
        assertThatThrownBy(() -> LottoNumber.from(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1~45 사이의 값이어야 합니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, true",
            "1, 2, false"
    })
    @DisplayName("로또 번호가 같으면 참, 다르면 거짓이 반환되어야 한다")
    void testLottoNumberIsEquals(int number1, int number2, boolean expected) {
        LottoNumber testNumber = LottoNumber.from(number1);

        boolean actual = testNumber.equals(LottoNumber.from(number2));

        assertThat(actual).isEqualTo(expected);
    }

}
