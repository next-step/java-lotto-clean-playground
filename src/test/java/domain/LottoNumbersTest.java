package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumbersTest {

    @Test
    @DisplayName("올바른 숫자 6개로 생성 시 LottoNumbers 객체가 정상 생성된다")
    void create_LottoNumbers_successfully() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // When
        LottoNumbers lottoNumbers = new LottoNumbers(numbers);

        // Then
        assertThat(lottoNumbers.getNumbers()).containsExactlyElementsOf(numbers);
    }

    @Test
    @DisplayName("로또 번호가 6개가 아닌 경우 예외를 던진다")
    void throw_exception_when_numbers_are_not_six() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5); // 5개

        // When & Then
        assertThatThrownBy(() -> new LottoNumbers(numbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 번호는 6개여야 합니다.");
    }


    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46, 100})
    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외를 던진다")
    void throw_exception_when_number_is_out_of_range(int invalidNumber) {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, invalidNumber);

        // When & Then
        assertThatThrownBy(() -> new LottoNumbers(numbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 번호는 1~45 사이여야 합니다.");
    }

    @Test
    @DisplayName("중복된 번호가 있을 경우 예외를 던진다")
    void throw_exception_when_duplicate_number_exists() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        // When & Then
        assertThatThrownBy(() -> new LottoNumbers(numbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("중복된 번호는 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("당첨 번호와 비교해 일치 개수를 계산하고 Rank를 반환한다")
    void return_correct_rank_by_counting_matches() {
        // Given
        LottoNumbers ticketNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers winningNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        boolean isBonusMatched = false;

        // When
        Rank rank = ticketNumbers.countMatch(winningNumbers, isBonusMatched);

        // Then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("두 LottoNumbers 객체가 동일한 번호를 가질 경우 equals와 hashCode가 동일하다")
    void equals_and_hashCode_should_work_correctly() {
        // Given
        LottoNumbers a = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers b = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));

        // Then
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }
}
