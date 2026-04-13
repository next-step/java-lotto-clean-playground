package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @DisplayName("당첨번호와 보너스번호를 넣으면 일치여부를 정확히 계산한다.")
    @ParameterizedTest
    @MethodSource("createCorrectCountTestMethodSource")
    void createCorrectCountTest(List<Ball> winnerNumbers, Ball bonusBall, int expectedCorrectCount, boolean expectedHasBonusNumber) {
        // given
        Ticket ticket = new Ticket(Stream.of(1, 2, 3, 4, 5, 6).map(Ball::new).toList());
        Ticket winnerTicket = new Ticket(winnerNumbers);
        WinnerBalls winnerBalls = new WinnerBalls(winnerTicket, bonusBall);

        // when
        CorrectCount correctCount = ticket.createCorrectCount(winnerBalls);

        // then
        assertAll(
                () -> Assertions.assertThat(correctCount.getCorrectCount()).isEqualTo(expectedCorrectCount),
                () -> Assertions.assertThat(correctCount.hasBonusNumber()).isEqualTo(expectedHasBonusNumber)
        );
    }

    static Stream<Arguments> createCorrectCountTestMethodSource() {
        return Stream.of(
                Arguments.arguments(Stream.of(40, 41, 1, 2, 3, 4).map(Ball::new).toList(),
                        new Ball(20),
                        4,
                        false),
                Arguments.arguments(Stream.of(40, 41, 1, 2, 3, 4).map(Ball::new).toList(),
                        new Ball(5),
                        4,
                        true),
                Arguments.arguments(Stream.of(40, 5, 1, 2, 3, 4).map(Ball::new).toList(),
                        new Ball(20),
                        5,
                        false),
                Arguments.arguments(Stream.of(40, 5, 1, 2, 3, 4).map(Ball::new).toList(),
                        new Ball(6),
                        5,
                        true)
        );
    }
}
