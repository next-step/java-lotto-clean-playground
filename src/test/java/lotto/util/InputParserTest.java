package lotto.util;

import lotto.domain.Lotto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputParserTest {

  @ParameterizedTest
  @ValueSource(strings = {"1,2,3,4,5,6", "11,22,33,44,12,45"})
  void 로또_당첨번호_입력시_로또결과_컬렉션으로_반환(String input) {
    final Lotto results = InputParser.parseLottoResult(input);

    assertThat(results.size()).isEqualTo(6);
  }

  @ParameterizedTest
  @ValueSource(strings = {"1,2,3,4,5,6", "11,22,33,44,12,45"})
  void 로또_당첨번호_입력시_로또_컬렉션으로_반환(String input) {
    final Lotto results = InputParser.parseLotto(input);

    assertThat(results.size()).isEqualTo(6);
  }

  @ParameterizedTest
  @ValueSource(strings = {" ", "     "})
  void 빈_문자열_입력시_예외가_발생한다(String input) {
    assertThatThrownBy(() -> InputParser.parseLottoResult(input))
        .isInstanceOf(RuntimeException.class);
  }

  @ParameterizedTest
  @ValueSource(strings = {"1", "1,2,3,4,5"})
  void 숫자가_6개가_아닐시_예외가_발생한다(String input) {
    assertThatThrownBy(() -> InputParser.parseLottoResult(input))
        .isInstanceOf(RuntimeException.class);
  }
}