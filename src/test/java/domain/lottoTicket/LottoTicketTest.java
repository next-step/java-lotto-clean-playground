package domain.lottoTicket;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lottoNumber.LottoNumber;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    @Test
    @DisplayName("로또 티켓 객체 생성 테스트")
    public void lottoTicket() {
        //given
        LottoNumber lottoNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));
        LottoTicket lottoTicket = new LottoTicket(lottoNumber);

        //when

        //then
        assertThat(lottoNumber).isEqualTo(lottoTicket.getLottoTicketNumber());
    }
}
