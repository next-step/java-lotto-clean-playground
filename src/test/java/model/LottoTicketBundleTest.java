package model;

import org.junit.jupiter.api.Test;

import static model.LottoFixture.기본로또;
import static org.junit.jupiter.api.Assertions.*;

class LottoTicketBundleTest {

    @Test
    void 저장소에_로또번호를_추가하면_읽을때_사이즈가_증가한다() {
        // given
        LottoTicketBundle repository = new LottoTicketBundle();
        LottoTicket lottoTicket = 기본로또();

        // when
        repository.addLottoNumbers(lottoTicket);

        // then
        assertEquals(1, repository.readLottoNumbersRepository().size());
    }
}
