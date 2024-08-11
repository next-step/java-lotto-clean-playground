package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.Errors;

class LottoNumberTest {

    @ParameterizedTest(name = "{0}은 로또 범위 안의 숫자이므로 정상적으로 로또 번호가 생성된다.")
    @ValueSource(ints = {1, 45, 10, 15})
    @DisplayName("로또 범위 안의 숫자로 로또 번호를 만들 수 있다.")
    void createTest(int lottoNumberValue) {
        //  given
        // when
        final LottoNumber lottoNumber = new LottoNumber(lottoNumberValue);
        // then
        assertThat(lottoNumber.getValue())
            .isEqualTo(lottoNumberValue);
    }

    @ParameterizedTest(name = "{0}은 로또 범위 밖의 숫자이므로 예외가 발생한다.")
    @ValueSource(ints = {0, -1, 48, 50})
    @DisplayName("로또 범위 밖의 숫자로 로또 번호를 만들면 예외가 발생한다.")
    void invalidCreateTest(int lottoNumberValue) {
        //  given
        // when
        // then
        assertThatThrownBy(() -> new LottoNumber(lottoNumberValue))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.NUMBER_IS_NOT_IN_VALID_RANGE);
    }

}
