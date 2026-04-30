package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest {

    @DisplayName("로또 만들기 테스트")
    @Test
    public void testMakingLotto() {
        //given
        List<Integer> sequence = List.of(8, 9, 10, 11, 12, 13);
        LottoGenerator lottoGenerator = new FixedLottoGenerator(sequence);
        LottoService lottoService = new LottoService(lottoGenerator);

        //when
        List<LottoTicket> tickets = lottoService.buyTickets(
                2000,
                List.of(List.of(1, 5, 3, 2, 6, 7))
        );

        //then
        assertThat(tickets.get(0).toDisplayString()).isEqualTo("[1, 2, 3, 5, 6, 7]");
        assertThat(tickets.get(1).toDisplayString()).isEqualTo("[8, 9, 10, 11, 12, 13]");
    }

    @Test
    @DisplayName("구입 금액이 1000원 미만이면 예외가 발생한다")
    void testMoneyLessThanThousand() {
        // given
        LottoGenerator generator = new FixedLottoGenerator(List.of(1, 2, 3, 4, 5, 6));
        LottoService lottoService = new LottoService(generator);

        // when & then
        assertThatThrownBy(() -> lottoService.buyTickets(500, List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1000원 이상의 금액을 입력해주세요.");
    }

    @Test
    @DisplayName("수동 구매 개수가 구매 가능한 티켓 수를 초과하면 예외가 발생한다")
    void testManualCountOverTicketCount() {
        // given
        LottoGenerator generator = new FixedLottoGenerator(List.of(1, 2, 3, 4, 5, 6));
        LottoService lottoService = new LottoService(generator);

        // when & then
        assertThatThrownBy(() -> lottoService.buyTickets(
                1000,
                List.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(7, 8, 9, 10, 11, 12)
                )
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("수동 구매 수량이 구입 금액을 초과할 수 없습니다.");
    }
}
