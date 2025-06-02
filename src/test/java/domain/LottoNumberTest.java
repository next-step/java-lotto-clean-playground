package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 100})
    @DisplayName("로또 번호 중 1~45 범위를 벗어나는 숫자가 있으면 예외를 던진다")
    void throw_exception_when_number_is_out_of_range(int invalid) {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, invalid);

        // When & Then
        assertThatThrownBy(() -> new LottoNumber(numbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 번호는 1~45 사이여야 합니다.");
    }

    @Test
    @DisplayName("올바른 숫자 6개로 생성 시 LottoNumbers 객체가 정상 생성된다")
    void create_LottoNumbers_successfully() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // When
        LottoNumber lottoNumber = new LottoNumber(numbers);

        // Then
        assertThat(lottoNumber.getNumbers()).containsExactlyElementsOf(numbers);
    }

    @Test
    @DisplayName("로또 번호가 6개가 아닌 경우 예외를 던진다")
    void throw_exception_when_numbers_are_not_six() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5); // 5개

        // When & Then
        assertThatThrownBy(() -> new LottoNumber(numbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("중복된 번호가 있을 경우 예외를 던진다")
    void throw_exception_when_duplicate_number_exists() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        // When & Then
        assertThatThrownBy(() -> new LottoNumber(numbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("중복된 번호는 입력할 수 없습니다.");
    }
}
