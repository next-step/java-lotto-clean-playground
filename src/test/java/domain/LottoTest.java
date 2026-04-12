package domain;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {
    @DisplayName("입력 개수만큼 로또 티켓을 발급한다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 3000, 10000, 600000})
    public void testLotto(int price) {
        // given
        LottoTicketGenerator lottoTicketGenerator = new RandomLottoTicketGenerator();
        Lotto lotto = new Lotto(new Price(price), lottoTicketGenerator, new ArrayList<>());

        // when
        int actual = lotto.getNumberOfTickets();
        int expected = price / 1000;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("티켓 리스트를 당첨 티켓과 비교하여 LottoResult를 반환한다.")
    @ParameterizedTest
    @MethodSource
    public void testGetResults(List<LottoTicket> tickets, LottoTicket winnerTicket, LottoNumber bonusNumber, LottoResult expected) {
        // given
        LottoTicketGenerator lottoTicketGenerator = new RandomLottoTicketGenerator();
        Lotto lotto = new Lotto(new Price(tickets.size() * 1000), lottoTicketGenerator, tickets);

        // when
        LottoResult actual = lotto.getResults(winnerTicket, bonusNumber);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testGetResults() {
        return Stream.of(
                Arguments.arguments(
                        List.of(
                                new LottoTicket(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6))),
                                new LottoTicket(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(7))),
                                new LottoTicket(Arrays.asList(LottoNumber.valueOf(7), LottoNumber.valueOf(8), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6))),
                                new LottoTicket(Arrays.asList(LottoNumber.valueOf(8), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6))),
                                new LottoTicket(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(14), LottoNumber.valueOf(15), LottoNumber.valueOf(16))),
                                new LottoTicket(Arrays.asList(LottoNumber.valueOf(11), LottoNumber.valueOf(12), LottoNumber.valueOf(13), LottoNumber.valueOf(14), LottoNumber.valueOf(15), LottoNumber.valueOf(6))),
                                new LottoTicket(Arrays.asList(LottoNumber.valueOf(11), LottoNumber.valueOf(21), LottoNumber.valueOf(31), LottoNumber.valueOf(41), LottoNumber.valueOf(15), LottoNumber.valueOf(16)))
                        ),
                        new LottoTicket(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6))),
                        LottoNumber.valueOf(7),
                        new LottoResult(List.of(1, 1, 1, 1, 1))
                ),
                Arguments.arguments(
                        List.of(
                                new LottoTicket(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)))
                        ),
                        new LottoTicket(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6))),
                        LottoNumber.valueOf(7),
                        new LottoResult(List.of(0, 0, 0, 0, 1))
                )
        );
    }
}
