package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumbersTest {

    @Test
    void 로또번호가_6개면_정상적으로_생성된다() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));

        LottoNumbers lottoNumbers = new LottoNumbers(numbers);

        assertEquals(6, lottoNumbers.getNumbers().size());
    }

    @Test
    void 로또번호가_6개가_아니면_예외가_발생한다() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4));

        assertThrows(IllegalArgumentException.class,
                () -> new LottoNumbers(numbers));
    }

    @Test
    void sortNumbers는_번호를_오름차순으로_정렬한다() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(6), new LottoNumber(3), new LottoNumber(5),
                new LottoNumber(1), new LottoNumber(4), new LottoNumber(2)
        );

        LottoNumbers sorted = new LottoNumbers(numbers).sortNumbers();

        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6),
                sorted.getNumbers().stream().map(LottoNumber::getNumber).toList());
    }

    @Test
    void 같은번호면_동일한객체() {
        // Given
        LottoNumbers lotto1 = new LottoNumbers(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        LottoNumbers lotto2 = new LottoNumbers(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));

        // When & Then
        assertEquals(lotto1, lotto2);
        assertEquals(lotto1.hashCode(), lotto2.hashCode());
    }
}
