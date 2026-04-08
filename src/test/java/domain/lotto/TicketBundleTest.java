package domain.lotto;

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
    void lottoResulTest(List<Number> winnerNumbers, Number bonusBall, List<Integer> expectedResult) {
        // given
        TicketBundle bundle = new TicketBundle();
        bundle.createRandomTickets(new TicketCount(new Payment(1000)), (count, min, max) -> List.of(1, 2, 3, 4, 5, 6));
        Ticket winnerTicket = new Ticket(winnerNumbers);
        WinnerBalls winnerBalls = new WinnerBalls(winnerTicket, bonusBall);

        // when
        Result result = bundle.createResult(winnerBalls);

        // then
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(result.getThreeCorrectCount()).isEqualTo(expectedResult.get(0)),
                () -> Assertions.assertThat(result.getFourCorrectCount()).isEqualTo(expectedResult.get(1)),
                () -> Assertions.assertThat(result.getFiveAndNoBonusCorrectCount()).isEqualTo(expectedResult.get(2)),
                () -> Assertions.assertThat(result.getFiveAndBonusCorrectCount()).isEqualTo(expectedResult.get(3)),
                () -> Assertions.assertThat(result.getSixCorrectCount()).isEqualTo(expectedResult.get(4))
        );
    }

    private static Stream<Arguments> winnerTicketAndLottoResultMethodSource() {
        return Stream.of(
                Arguments.of(Stream.of(40, 41, 42, 3, 2, 1)
                                .map(Number::new)
                                .toList(),
                        new Number(20),
                        List.of(1, 0, 0, 0, 0)),
                Arguments.of(Stream.of(40, 41, 4, 3, 2, 1)
                                .map(Number::new)
                                .toList(),
                        new Number(20),
                        List.of(0, 1, 0, 0, 0)),
                Arguments.of(Stream.of(40, 5, 4, 3, 2, 1)
                                .map(Number::new)
                                .toList(),
                        new Number(20),
                        List.of(0, 0, 1, 0, 0)),
                Arguments.of(Stream.of(40, 5, 4, 3, 2, 1)
                                .map(Number::new)
                                .toList(),
                        new Number(6),
                        List.of(0, 0, 0, 1, 0)),
                Arguments.of(Stream.of(6, 5, 4, 3, 2, 1)
                                .map(Number::new)
                                .toList(),
                        new Number(20),
                        List.of(0, 0, 0, 0, 1))
        );
    }
}
