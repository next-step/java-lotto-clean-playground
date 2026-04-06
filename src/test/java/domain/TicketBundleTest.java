package domain;

import domain.lotto.Number;
import domain.lotto.Ticket;
import domain.lotto.TicketBundle;
import domain.lotto.wrappers.Payment;
import domain.lotto.wrappers.Result;
import domain.lotto.wrappers.TicketCount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class TicketBundleTest {

    @DisplayName("당첨 번호 조합에 따라 3~6개 일치 당첨 횟수가 정확히 계산된다.")
    @ParameterizedTest
    @MethodSource("winnerTicketAndLottoResultMethodSource")
    void lottoResulTest(List<Number> winnerNumbers, List<Integer> expectedResult) {
        // Given
        TicketBundle bundle = new TicketBundle();
        bundle.createRandomTickets(new TicketCount(new Payment(1000)), (count, min, max) -> List.of(1, 2, 3, 4, 5, 6));
        Ticket winnerTicket = new Ticket(winnerNumbers);

        // When
        Result result = bundle.createResult(winnerTicket);

        // Then
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(result.getThreeCorrectCount()).isEqualTo(expectedResult.get(0)),
                () -> Assertions.assertThat(result.getFourCorrectCount()).isEqualTo(expectedResult.get(1)),
                () -> Assertions.assertThat(result.getFiveCorrectCount()).isEqualTo(expectedResult.get(2)),
                () -> Assertions.assertThat(result.getSixCorrectCount()).isEqualTo(expectedResult.get(3))
        );
    }

    private static Stream<Arguments> winnerTicketAndLottoResultMethodSource() {
        return Stream.of(
                Arguments.of(Stream.of(1, 2, 3, 40, 41, 42)
                                .map(Number::new)
                                .toList(),
                        List.of(1, 0, 0, 0)),
                Arguments.of(Stream.of(1, 2, 3, 4, 41, 42)
                                .map(Number::new)
                                .toList(),
                        List.of(0, 1, 0, 0)),
                Arguments.of(Stream.of(1, 2, 3, 4, 5, 42)
                                .map(Number::new)
                                .toList(),
                        List.of(0, 0, 1, 0)),
                Arguments.of(Stream.of(1, 2, 3, 4, 5, 6)
                                .map(Number::new)
                                .toList(),
                        List.of(0, 0, 0, 1))
        );
    }
}
