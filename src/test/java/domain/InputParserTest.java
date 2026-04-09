package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @Test
    @DisplayName("쉼표와 공백으로 구분된 문자열을 로또 객체로 변환한다.")
    void parseToLottoTest() {
        //given
        String input = "1, 2, 3, 4, 5, 6";

        //when
        Lotto result = InputParser.parseToLotto(input);

        //then
        assertThat(result.getNumbers()).hasSize(6);
        assertThat(result.getNumbers())
                .extracting("number")
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("숫자 문자열을 로또 번호 객체로 변환한다.")
    void parseToLottoNumberTest() {
        //given
        String input = "7";

        //when
        LottoNumber result = InputParser.parseToLottoNumber(input);

        //then
        assertThat(result).isEqualTo(new LottoNumber(7));
    }
}
