package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersTest {
    @Test
    @DisplayName("로또 번호가 6개면 객체 생성 성공")
    void createLottoNumbers_validSize_success() {
        //Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        //When
        LottoNumbers lottoNumbers = new LottoNumbers(numbers);

        //Then
        assertThat(lottoNumbers).isNotNull();
    }

    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외를 던진다")
    void throwsException_when_LottoNumberCount_isNot6() {
        //Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);

        //When & Then
        assertThatThrownBy(() -> new LottoNumbers(numbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호에 1보다 작거나 45보다 큰 숫자가 있으면 예외가 발생한다")
    void throwsException_when_input_LottoNum_outOfRange() {
        //Given
        List<Integer> numbers = List.of(0, 2, 3, 4, 5, 46);

        //When & Then
        assertThatThrownBy(() -> new LottoNumbers(numbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 번호는 1~45 사이여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호를 비교해 일치하는 번호 개수를 반환한다")
    void return_CorrectMatchCount_when_Compare_LottoNumber() {
        // Given
        LottoNumbers myNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers winningNumbers = new LottoNumbers(List.of(4, 5, 6, 7, 8, 9));

        // When
        int matchCount = myNumbers.countMatch(winningNumbers);

        // Then
        assertThat(matchCount).isEqualTo(3);
    }
}
