package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoResultTest {
    @DisplayName("입력 리스트의 길이가 LottoRank 종류의 수와 같다면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource
    public void testLottoResult_ValidInputLength(List<Count> matchingTicketCounts) {
        // when & then
        assertThatCode(() -> new LottoResult(matchingTicketCounts)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> testLottoResult_ValidInputLength() {
        return Stream.of(
                Arguments.arguments(List.of(new Count(1), new Count(2), new Count(1), new Count(0))),
                Arguments.arguments(List.of(new Count(0), new Count(0), new Count(0), new Count(0))),
                Arguments.arguments(List.of(new Count(4), new Count(3), new Count(2), new Count(1)))
        );
    }

    @DisplayName("입력 리스트의 길이가 LottoRank 종류의 수와 다르다면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @MethodSource
    public void testLottoResult_InvalidInputLength(List<Count> matchingTicketCounts) {
        // when & then
        assertThatThrownBy(() -> new LottoResult(matchingTicketCounts)).isInstanceOf(IllegalArgumentException.class).hasMessage("등수 종류의 수가 올바르지 않습니다.");
    }

    private static Stream<Arguments> testLottoResult_InvalidInputLength() {
        return Stream.of(
                Arguments.arguments(List.of()),
                Arguments.arguments(List.of(new Count(0), new Count(0))),
                Arguments.arguments(List.of(new Count(4), new Count(3), new Count(2), new Count(1), new Count(0)))
        );
    }
}
