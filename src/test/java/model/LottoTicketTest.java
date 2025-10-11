package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


import static model.LottoFixture.기본로또;
import static org.junit.jupiter.api.Assertions.*;

class LottoTicketTest {

    @Test
    void 로또번호가_6개면_정상적으로_생성된다() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));

        LottoTicket lottoTicket = new LottoTicket(numbers);

        assertEquals(6, lottoTicket.getNumbers().size());
    }

    @Test
    void 로또번호가_6개가_아니면_예외가_발생한다() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4));

        assertThrows(IllegalArgumentException.class,
                () -> new LottoTicket(numbers));
    }

    @Test
    void sortNumbers는_번호를_오름차순으로_정렬한다() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(6), new LottoNumber(3), new LottoNumber(5),
                new LottoNumber(1), new LottoNumber(4), new LottoNumber(2)
        );

        LottoTicket sorted = new LottoTicket(numbers).sortNumbers();

        assertEquals(기본로또(), sorted);
    }

    @Test
    void 같은번호면_동일한객체() {
        // Given
        LottoTicket lotto1 = 기본로또();
        LottoTicket lotto2 = 기본로또();

        // When & Then
        assertEquals(lotto1, lotto2);
        assertEquals(lotto1.hashCode(), lotto2.hashCode());
    }
}
