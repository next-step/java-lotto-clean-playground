package utils;

import domain.LottoNumber;
import domain.LottoNumbers;
import domain.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersParserTest {

    @Test
    @DisplayName("쉼표로 구분된 6개의 숫자를 파싱하여 WinningNumbers를 생성한다")
    void parseValidInput() {
        WinningNumbers result = WinningNumbersParser.parse("1, 2, 3, 4, 5, 6");

        assertThat(result.countMatch(toLottoNumbers(List.of(1, 2, 3, 10, 11, 12))))
                .isEqualTo(3);
    }

    @Test
    @DisplayName("숫자가 아닌 문자가 포함되면 예외가 발생한다")
    void parseWithNonNumericInput() {
        assertThatThrownBy(() -> WinningNumbersParser.parse("1, 2, a, 4, 5, 6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 쉼표(,)로 구분된 숫자 6개여야 합니다.");
    }

    @Test
    @DisplayName("숫자가 6개보다 적으면 예외가 발생한다")
    void parseWithLessThanSixNumbers() {
        assertThatThrownBy(() -> WinningNumbersParser.parse("1, 2, 3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 쉼표(,)로 구분된 숫자 6개여야 합니다.");
    }

    @Test
    @DisplayName("숫자가 6개보다 많으면 예외가 발생한다")
    void parseWithMoreThanSixNumbers() {
        assertThatThrownBy(() -> WinningNumbersParser.parse("1, 2, 3, 4, 5, 6, 7"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 쉼표(,)로 구분된 숫자 6개여야 합니다.");
    }

    @Test
    @DisplayName("공백이 있어도 정상 파싱된다")
    void parseWithWhitespace() {
        WinningNumbers result = WinningNumbersParser.parse(" 1 , 2 , 3 , 4 , 5 , 6 ");

        assertThat(result.countMatch(toLottoNumbers(List.of(1, 2, 3, 7, 8, 9))))
                .isEqualTo(3);
    }

    private LottoNumbers toLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        return new LottoNumbers(lottoNumbers);
    }
}
