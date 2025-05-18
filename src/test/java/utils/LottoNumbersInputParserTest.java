package utils;

import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumbersInputParserTest {

    @Test
    @DisplayName("유효한 로또 번호 문자열을 파싱하면 정확한 LottoNumber 리스트가 반환된다.")
    void testParse_validInput() {
        String input = "1, 2, 3, 4, 5, 6";
        List<LottoNumber> lottoNumbers = LottoNumbersInputParser.parse(input);

        assertThat(lottoNumbers)
                .hasSize(6)
                .extracting(LottoNumber::value)
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("공백이 포함된 로또 번호 문자열을 파싱해도 정확한 LottoNumber 리스트가 반환된다.")
    void testParse_inputWithSpaces() {
        String input = " 1 , 2 , 3 , 4 , 5 , 6 ";
        List<LottoNumber> lottoNumbers = LottoNumbersInputParser.parse(input);

        assertThat(lottoNumbers)
                .hasSize(6)
                .extracting(LottoNumber::value)
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("숫자가 아닌 값이 포함된 로또 번호 문자열을 파싱하면 NumberFormatException이 발생한다.")
    void testParse_invalidInput_nonNumeric() {
        String input = "1, 2, 3, 4, a, 6";

        assertThrows(NumberFormatException.class, () -> LottoNumbersInputParser.parse(input));
    }

    @Test
    @DisplayName("로또 번호가 부족한 입력을 파싱하면, 파싱된 리스트의 크기가 6이 아닌지 확인한다.")
    void testParse_invalidInput_notEnoughNumbers() {
        String input = "1, 2, 3, 4, 5";

        List<LottoNumber> lottoNumbers = LottoNumbersInputParser.parse(input);
        assertThat(lottoNumbers).hasSize(5);
    }
}
