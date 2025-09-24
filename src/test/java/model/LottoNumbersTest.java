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
    void getNumbers_반환값은_불변리스트이다() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));

        LottoNumbers lottoNumbers = new LottoNumbers(numbers);

        assertThrows(UnsupportedOperationException.class,
                () -> lottoNumbers.getNumbers().add(new LottoNumber(7)));
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
}
