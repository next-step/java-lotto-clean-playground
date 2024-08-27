package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @DisplayName("로또번호 생성 테스트 : 1~45 사이 수에 정상 수행")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 24, 45})
    public void creationTest(int value) {
        LottoNumber lottoNumber = new LottoNumber(value);
        assertThat(lottoNumber.getNumber()).isEqualTo(value);
    }

    @DisplayName("로또번호 생성 테스트 : 1~45 범위 외의 수에 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 0, 999})
    public void outOfRangeTest(int value) {
        assertThatThrownBy(() -> new LottoNumber(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
