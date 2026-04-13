package domain.lotto;

import domain.lotto.exception.TicketSizeMismatchException;
import number_generator.NumberListGenerator;
import number_generator.RandomNumberListGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class TicketGeneratorTest {

    @DisplayName("자동 구매로 요청한 수만큼 자동으로 티켓을 생성한다.")
    @ParameterizedTest
    @MethodSource("createRandomTicketsMethodSource")
    void createRandomTickets(TicketCount randomTicketCount) {
        // given
        TicketGenerator generator = new TicketGenerator(new TicketCount(0), randomTicketCount);
        NumberListGenerator randomNumberListGenerator = new RandomNumberListGenerator();

        // when
        List<Ticket> tickets = generator.createRandomTickets(randomNumberListGenerator);

        // then
        Assertions.assertThat(tickets.size()).isEqualTo(randomTicketCount.getValue());
    }

    static Stream<Arguments> createRandomTicketsMethodSource() {
        return Stream.of(
                Arguments.of(new TicketCount(5)),
                Arguments.of(new TicketCount(6)),
                Arguments.of(new TicketCount(7)),
                Arguments.of(new TicketCount(8))
        );
    }

    @DisplayName("수동 구매로 요청한 대로 티켓을 생성해준다.")
    @ParameterizedTest
    @MethodSource("createManualTicketsMethodSource")
    void createManualTickets(List<Ticket> tickets) {
        // given
        TicketGenerator generator = new TicketGenerator(new TicketCount(3), new  TicketCount(0));

        // when
        List<Ticket> generatedTickets = new ArrayList<>(generator.createManualTickets(tickets));
        List<List<Ball>> generatedBalls = generatedTickets.stream().map(Ticket::getBalls).toList();
        List<List<Ball>> expectedBalls = tickets.stream().map(Ticket::getBalls).toList();

        // then
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(generatedBalls.get(0)).containsAll(expectedBalls.get(0)),
                () -> Assertions.assertThat(generatedBalls.get(1)).containsAll(expectedBalls.get(1)),
                () -> Assertions.assertThat(generatedBalls.get(2)).containsAll(expectedBalls.get(2))
        );
    }

    static Stream<Arguments> createManualTicketsMethodSource() {
        return Stream.of(
                Arguments.of(List.of(
                        new Ticket(Stream.of(1, 2, 3, 4, 5, 6).map(Ball::new).toList()),
                        new Ticket(Stream.of(30, 29, 28, 27, 26, 25).map(Ball::new).toList()),
                        new Ticket(Stream.of(17, 19, 32, 12, 26, 10).map(Ball::new).toList()))),
                Arguments.of(List.of(
                        new Ticket(Stream.of(30, 29, 28, 27, 26, 25).map(Ball::new).toList()),
                        new Ticket(Stream.of(1, 2, 3, 4, 5, 6).map(Ball::new).toList()),
                        new Ticket(Stream.of(12, 26, 10, 17, 19, 32).map(Ball::new).toList()))),
                Arguments.of(List.of(
                        new Ticket(Stream.of(30, 29, 28, 27, 26, 25).map(Ball::new).toList()),
                        new Ticket(Stream.of(30, 29, 28, 27, 26, 25).map(Ball::new).toList()),
                        new Ticket(Stream.of(30, 29, 28, 27, 26, 25).map(Ball::new).toList())))
        );
    }

    @DisplayName("수동 티켓 수와 실제 티켓 리스트의 크기가 다르면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("mismatchTicketCountMethodSource")
    void manualTicketSizeMismatchTest(int manualCount, int actualListSize) {
        // Given
        TicketCount manualTicketCount = new TicketCount(manualCount);
        TicketCount randomTicketCount = new TicketCount(0);
        TicketGenerator generator = new TicketGenerator(manualTicketCount, randomTicketCount);

        List<Ticket> manualTickets = new ArrayList<>();
        for (int i = 0; i < actualListSize; i++) {
            manualTickets.add(new Ticket(Stream.of(1, 2, 3, 4, 5, 6).map(Ball::new).toList()));
        }

        // When & Then
        Assertions.assertThatThrownBy(() -> generator.createManualTickets(manualTickets))
                .isInstanceOf(TicketSizeMismatchException.class);
    }

    private static Stream<Arguments> mismatchTicketCountMethodSource() {
        return Stream.of(
                Arguments.of(3, 2),
                Arguments.of(1, 5),
                Arguments.of(5, 0)
        );
    }
}
