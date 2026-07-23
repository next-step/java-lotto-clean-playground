package domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTest {
    @Test
    void 번호가_6개이면_생성된다() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        // When & Then
        assertDoesNotThrow(() -> new Lotto(numbers));
    }
    @Test
    void 번호가_6개보다_적으면_생성되지_않는다() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(numbers)
        );
        assertEquals("로또 번호는 6개여야 합니다.", exception.getMessage());
    }
    @Test
    void 번호가_6개보다_많으면_생성되지_않는다() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(numbers)
        );
        assertEquals("로또 번호는 6개여야 합니다.", exception.getMessage());
    }

    @Test
    void 번호가_정상범위인_경우_생성된다() {
        // Given
        List<Integer> numbers = List.of(1, 7, 16, 24, 32, 45);
        // When & Then
        assertDoesNotThrow(() -> new Lotto(numbers));
    }
    @Test
    void 번호가_1보다_작으면_생성되지_않는다() {
        // Given
        List<Integer> numbers = List.of(0, 1, 2, 3, 4, 5);
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(numbers)
        );
        assertEquals("로또 번호는 1~45 사이여야 합니다.", exception.getMessage());
    }
    @Test
    void 번호가_45보다_크면_생성되지_않는다() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(numbers)
        );
        assertEquals("로또 번호는 1~45 사이여야 합니다.", exception.getMessage());
    }

    @Test
    void 중복된_숫자가_존재하면_생성되지_않는다() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(numbers)
        );
        assertEquals("로또 번호는 중복될 수 없습니다.", exception.getMessage());
    }
}
