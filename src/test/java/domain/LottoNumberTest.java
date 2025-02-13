package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 20, 45})
    @DisplayName("OK : 로또 번호를 생성한다.")
    void newLottoNumber(int number) {
        LottoNumber lottoNumber = new LottoNumber(number);
        assertThat(lottoNumber.getNumber()).isEqualTo(number);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("ERROR : 로또 번호 범위에 벗어나면 에러가 발생한다.")
    void newLottoNumberOutOfRange(int number) {
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(number));
    }
}