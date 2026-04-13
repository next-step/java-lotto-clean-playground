package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 22, 45})
    @DisplayName("1부터 45 사이의 숫자로 로또 번호를 생성한다.")
    void createLottoNumberTest(int number) {
        //given //when
        LottoNumber lottoNumber = new LottoNumber(number);

        //then
        assertThat(lottoNumber.getNumber()).isEqualTo(number);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("로또 번호가 범위를 벗어나면 예외가 발생한다.")
    void validatorNumberTest(int number) {
        //given //when //then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호가 범위를 벗어났습니다.");
    }

    @Test
    @DisplayName("번호가 같으면 동일한 객체로 판단한다.")
    void equalsAndHashCodeTest() {
        //given
        LottoNumber first = new LottoNumber(10);
        LottoNumber second = new LottoNumber(10);

        //when //then
        assertThat(first).isEqualTo(second);
        assertThat(first.hashCode()).isEqualTo(second.hashCode());
    }

    @Test
    @DisplayName("번호를 문자열로 변환하여 반환한다.")
    void toStringTest() {
        //given
        LottoNumber lottoNumber = new LottoNumber(7);

        //when
        String result = lottoNumber.toString();

        //then
        assertThat(result).isEqualTo("7");
    }
}
