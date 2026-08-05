package domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class LottoTest {
    @Test
    @DisplayName("로또 번호가 중복되면 예외가 발생한다")
    void throwsExceptionWhenLottoNumbersContainDuplicates() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(numbers)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidLottoNumbers")
    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다")
    void throwsExceptionWhenLottoNumberCountIsNotSix(List<Integer> numbers) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(numbers)
        );
    }

    static Stream<List<Integer>> invalidLottoNumbers() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5),
                List.of(1, 2, 3, 4, 5, 6, 7)
        );
    }
}
