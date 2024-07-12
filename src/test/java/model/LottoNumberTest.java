package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @DisplayName("lotto number가 1과 45 사이의 숫자가 아니면 예외를 발생한다.")
    @ValueSource(ints = {46, 0})
    @ParameterizedTest
    void lotto_number_with_not_1_to_45(int number) {
        // given
        // when
        // then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호 및 보너스 볼은 1과 45사이의 숫자이어야 합니다.");
    }

    @DisplayName("lotto number가 숫자 이외의 입력을 받으면 예외를 발생한다.")
    @ValueSource(strings = {"!", "ㅁ", "a",})
    @ParameterizedTest
    void lotto_number_with_not_number_input(String input) {
        // given
        // when
        // then
        assertThatThrownBy(() -> LottoNumber.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자입력만 허용합니다.");
    }
}
