package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.LottoNumberGenerator;
import util.FixedLottoNumberGenerator;

import java.util.List;


class LottoServiceTest {

    @DisplayName("로또 만들기 테스트")
    @Test
    public void testMakingLotto() {
        //given
        List<Integer> sequence = List.of(1, 5, 3, 2, 6, 7);
        LottoNumberGenerator lottoNumberGenerator = new FixedLottoNumberGenerator(sequence);
        LottoService lottoService = new LottoService(lottoNumberGenerator);

        //when
        List<LottoTicket> tickets = lottoService.buyTickets(1000);

        //then
        assertThat(tickets.get(0).toDisplayString()).isEqualTo("[1, 2, 3, 5, 6, 7]");

    }
    @Test
    @DisplayName("구입 금액이 1000원 이상이 아니면 예외가 발생한다")
    void throwExceptionWhenMoneyIsNotUnitOfThousand() {
        // given
        LottoNumberGenerator generator = new FixedLottoNumberGenerator(List.of(1, 2, 3, 4, 5, 6));
        LottoService lottoService = new LottoService(generator);

        // when & then
        assertThatThrownBy(() -> lottoService.buyTickets(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1000원 이상의 금액을 입력해주세요.");
    }
}
