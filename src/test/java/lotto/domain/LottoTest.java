package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoTest {

  @Test
  void 로또_번호_6개를_가지고_있는지_확인() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

    Lotto lotto = Lotto.of(numbers);

    assertThat(lotto.size()).isEqualTo(6);
  }

  @Test
  void 로또_번호가_6개가_아닌_경우_예외_발생() {
    List<Integer> invalidNumbers = Arrays.asList(1, 2, 3, 4, 5);

    assertThatThrownBy(() -> Lotto.of(invalidNumbers))
        .isInstanceOf(RuntimeException.class);
  }

  @Test
  void 로또_번호에_중복된_숫자가_있는_경우_예외_발생() {
    List<Integer> duplicateNumbers = Arrays.asList(1, 2, 3, 4, 5, 5);

    assertThatThrownBy(() -> Lotto.of(duplicateNumbers))
        .isInstanceOf(RuntimeException.class);
  }

  @ParameterizedTest
  @CsvSource({"0", "46"})
  void 로또_번호가_1부터_45_범위를_벗어난_경우_예외_발생(int invalidNumber) {
    List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    numbers.add(invalidNumber);

    assertThatThrownBy(() -> Lotto.of(numbers))
        .isInstanceOf(RuntimeException.class);
  }

  @Test
  void numbers_메서드는_문자열_형태로_반환되어야_함() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    Lotto lotto = Lotto.of(numbers);

    String result = lotto.toString();

    assertThat(result).isEqualTo("[1, 2, 3, 4, 5, 6]");
  }
}