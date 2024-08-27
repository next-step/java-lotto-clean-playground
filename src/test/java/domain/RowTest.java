package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RowTest {

    private static Stream<Arguments> methodSourceOfCreate() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(1, 2, 3, 4, 5, 6)),
                Arguments.arguments(Arrays.asList(11, 23, 43, 12, 32, 22))
        );
    }

    @DisplayName("로또행 생성 테스트 : 6개 수에 정상 수행")
    @ParameterizedTest
    @MethodSource("methodSourceOfCreate")
    public void creationTest(List<Integer> numbers) {
        Row row = rowFromList(numbers);
        assertThat(row.getNums().size()).isEqualTo(6);
    }

    private static Stream<Arguments> methodSourceOfOverSize() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(1, 2, 3, 4, 5, 6, 7)),
                Arguments.arguments(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8))
        );
    }

    @DisplayName("로또행 생성 테스트 : 6개 이상 수에 예외 발생")
    @ParameterizedTest
    @MethodSource("methodSourceOfOverSize")
    public void overSizeTest(List<Integer> numbers) {
        assertThatThrownBy(() -> rowFromList(numbers))
                .isInstanceOf(RuntimeException.class);
    }

    private static Stream<Arguments> methodSourceOfDuplicate() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(1, 1, 3, 4, 5, 6)),
                Arguments.arguments(Arrays.asList(1, 2, 2, 2, 5, 6))
        );
    }

    @DisplayName("로또행 생성 테스트 : 중복된 수에 예외 발생")
    @ParameterizedTest
    @MethodSource("methodSourceOfDuplicate")
    public void duplicateTest(List<Integer> numbers) {
        assertThatThrownBy(() -> rowFromList(numbers))
                .isInstanceOf(RuntimeException.class);
    }

    private Row rowFromList(List<Integer> numbers) {
        return new Row(numbers.stream().map(num -> new LottoNumber(num)).collect(Collectors.toList()));
    }

}
