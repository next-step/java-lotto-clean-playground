package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

  @ParameterizedTest
  @DisplayName("로또 번호는 1부터 45 사이를 벗어나면 예외가 발생한다.")
  @ValueSource(ints = {0, 46})
  void lottoNumberShouldThrowExceptionWhenOutOfRange(int invalidNumber) {
    assertThatThrownBy(() -> new LottoNumber(invalidNumber))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("로또 번호는 1~45 사이여야 합니다.");
  }
}
