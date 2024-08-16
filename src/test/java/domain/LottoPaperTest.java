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

public class LottoPaperTest {

    private static Stream<Arguments> methodSourceOfWriteRow() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(1, 2, 3, 4, 5, 6)),
                Arguments.arguments(Arrays.asList(11, 23, 43, 12, 32, 22))
        );
    }

    @DisplayName("로또행 추가 테스트 : 정상 수행 시 row 추가")
    @ParameterizedTest
    @MethodSource("methodSourceOfWriteRow")
    public void writeRowTest(List<Integer> numbers) {
        LottoPaper lottoPaper = new LottoPaper();
        Row row = rowFromList(numbers);
        lottoPaper.writeRow(rowFromList(numbers));
        assertThat(lottoPaper.getRowNum()).isEqualTo(1);
    }

    private Row rowFromList(List<Integer> numbers) {
        return new Row(numbers.stream().map(num -> new LottoNumber(num)).collect(Collectors.toList()));
    }
}
