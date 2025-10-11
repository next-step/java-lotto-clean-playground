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

    @Test
    void 같은로또묶음들을가지면_동일한저장소다() {
        // Given
        LottoTicketBundle re1 = new LottoTicketBundle();
        LottoTicketBundle re2 = new LottoTicketBundle();


        re1.addLottoNumbers(기본로또());
        re2.addLottoNumbers(기본로또());

        // When & Then
        assertEquals(re1, re2);
        assertEquals(re1.hashCode(), re2.hashCode());
    }
}
