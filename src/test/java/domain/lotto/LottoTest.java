package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    private final int FIRST_LOTTO_POSITION = 0;
    private final int LAST_LOTTO_POSITION = 6;
    private final int MIN_LOTTO_NUMBER = 1;
    private final int MAX_LOTTO_NUMBER = 45;


    @Test
    @DisplayName("로또 객체 생성")
    public void lottoTest() {
        //given
        int expectedLottoTicketCount = 3;
        int money = 3000;

        //when
        LottoService lottoService = new LottoService();
        Lotto lotto = new Lotto(lottoService.countLottoTickets(money));

        //then
        assertThat(expectedLottoTicketCount).isEqualTo(lotto.getLottoTicketCount());
    }
}
