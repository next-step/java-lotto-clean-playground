package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoResultTest {
    @DisplayName("입력 리스트의 길이가 LottoRank 종류의 수와 같다면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource
    public void testLottoResult_ValidInputLength(List<Integer> matchingTicketCounts) {
        // when & then
        assertThatCode(() -> new LottoResult(matchingTicketCounts)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> testLottoResult_ValidInputLength() {
        return Stream.of(
                Arguments.arguments(List.of(1, 2, 1, 0, 0)),
                Arguments.arguments(List.of(0, 0, 0, 0, 0)),
                Arguments.arguments(List.of(5, 4, 3, 2, 1))
        );
    }

    @DisplayName("입력 리스트의 길이가 LottoRank 종류의 수와 다르다면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @MethodSource
    public void testLottoResult_InvalidInputLength(List<Integer> matchingTicketCounts) {
        // when & then
        assertThatThrownBy(() -> new LottoResult(matchingTicketCounts)).isInstanceOf(IllegalArgumentException.class).hasMessage("등수 종류의 수가 올바르지 않습니다.");
    }

    private static Stream<Arguments> testLottoResult_InvalidInputLength() {
        return Stream.of(
                Arguments.arguments(List.of()),
                Arguments.arguments(List.of(0, 0)),
                Arguments.arguments(List.of(5, 4, 3, 2, 1, 0))
        );
    }

    @DisplayName("로또 결과와 가격으로 수익률을 계산한다.")
    @ParameterizedTest
    @MethodSource
    public void testGetProfitRate(List<Integer> matchingTicketCounts, Price price, double expected) {
        // given
        LottoResult lottoResult = new LottoResult(matchingTicketCounts);

        // when
        double actual = lottoResult.getProfitRate(price);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testGetProfitRate() {
        return Stream.of(
                Arguments.arguments(List.of(1, 2, 1, 0, 0), new Price(10000), 160.5),
                Arguments.arguments(List.of(0, 0, 0, 0, 0), new Price(10000), 0.0),
                Arguments.arguments(List.of(2, 1, 2, 0, 1), new Price(10000), 200306.0)
        );
    }
}
