package domain.lotto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertSame;


class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void 최소값과_최대값일때_생성된다(int number) {
        assertDoesNotThrow(() -> LottoNumber.from(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 범위를_벗어나면_생성되지_않는다(int number) {

        assertThatThrownBy(() -> LottoNumber.from(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1~45 사이여야 합니다.");
    }

    @Test
    void from으로_조회하면_캐시된_동일_인스턴스를_반환한다() {
        LottoNumber number1 = LottoNumber.from(7);
        LottoNumber number2 = LottoNumber.from(7);

        assertSame(number1, number2);
    }
}
