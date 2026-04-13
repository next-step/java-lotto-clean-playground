package domain.lotto;

import domain.lotto.exception.DuplicateNumbersException;
import domain.lotto.exception.EmptyTicketException;
import domain.lotto.exception.WrongTicketLengthException;
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

    @ParameterizedTest
    @MethodSource("invalidBallsMethodSource")
    @DisplayName("유효하지 않은 번호 리스트로 티켓 생성 시 예외가 발생한다.")
    void constructorExceptionTest(List<Ball> invalidBalls, Class<? extends RuntimeException> exceptionType) {
        Assertions.assertThatThrownBy(() -> new Ticket(invalidBalls)).isInstanceOf(exceptionType);
    }

    private static Stream<Arguments> invalidBallsMethodSource() {
        return Stream.of(
                Arguments.of(
                        List.of(), EmptyTicketException.class
                ),
                Arguments.of(
                        Stream.of(1, 1, 2, 3, 4, 5).map(Ball::new).toList(), DuplicateNumbersException.class
                ),
                Arguments.of(
                        Stream.of(1, 2, 3, 4, 5).map(Ball::new).toList(), WrongTicketLengthException.class
                ),
                Arguments.of(
                        Stream.of(1, 2, 3, 4, 5, 6, 7).map(Ball::new).toList(), WrongTicketLengthException.class
                )
        );
    }
}
