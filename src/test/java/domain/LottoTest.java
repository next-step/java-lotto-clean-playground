package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

  @Test
  @DisplayName("자동 생성된 로또 번호는 6개여야 한다")
  void autoLottoShouldHave6Numbers() {
    // Given
    LottoNumberGenerator lottoNumberGenerator = LottoNumber.createLottoNumberGenerator();

    // When
    Lotto lotto = Lotto.createAuto(lottoNumberGenerator);

    // Then
    assertThat(lotto.getNumbers()).hasSize(6);
  }

  @Test
  @DisplayName("생성된 로또 번호는 6개여야 한다")
  void ManuallottoNumberShouldHave6Numbers() {
    // Given

    List<LottoNumber> invalidNumbersLess = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
        new LottoNumber(4), new LottoNumber(5)); // 5개
    List<LottoNumber> invalidNumbersMore = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6), new LottoNumber(7)); // 7개

    // When & Then
    assertThatThrownBy(() -> Lotto.createManual(invalidNumbersLess))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("로또 번호는 6개여야 합니다.");

    assertThatThrownBy(() -> Lotto.createManual(invalidNumbersMore))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("로또 번호는 6개여야 합니다.");
  }


  @Test
  @DisplayName("생성된 로또 번호에 중복이 있으면 예외가 발생한다")
  void manualLottoNumberShouldThrowExceptionWhenDuplicatesExist() {
    // Given
    List<LottoNumber> duplicateNumbers = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
        new LottoNumber(4), new LottoNumber(5), new LottoNumber(5));

    // When & Then
    assertThatThrownBy(() -> Lotto.createManual(duplicateNumbers))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("로또 번호는 중복될 수 없습니다.");
  }
}
