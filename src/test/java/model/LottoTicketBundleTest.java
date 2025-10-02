package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoTicketBundleTest {

    @Test
    void 저장소에_로또번호를_추가하면_읽을때_사이즈가_증가한다() {
        // given
        LottoTicketBundle repository = new LottoTicketBundle();
        LottoNumbers lottoNumbers = new LottoNumbers(Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));

        // when
        repository.addLottoNumbers(lottoNumbers);

        // then
        assertEquals(1, repository.readLottoNumbersRepository().size());
    }

    @Test
    void 같은로또묶음들을가지면_동일한저장소다() {
        // Given
        LottoTicketBundle re1 = new LottoTicketBundle();
        LottoTicketBundle re2 = new LottoTicketBundle();

        LottoNumbers lotto = new LottoNumbers(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));

        re1.addLottoNumbers(lotto);
        re2.addLottoNumbers(new LottoNumbers(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        )));

        // When & Then
        assertEquals(re1, re2);
        assertEquals(re1.hashCode(), re2.hashCode());
    }
}
