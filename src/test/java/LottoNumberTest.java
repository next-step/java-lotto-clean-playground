import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LottoNumberTest {

    @Test
    @DisplayName("유효한 번호로 LottoNumber를 생성")
    void lottoNumberWithValidNumber() {
        LottoNumber.from(1);
        LottoNumber.from(45);
    }

    @Test
    @DisplayName("1~45 범위를 벗어나면 예외")
    void throwExceptionNumberIsOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.from(0));

        assertThrows(IllegalArgumentException.class, () -> LottoNumber.from(46));
    }

    @Test
    @DisplayName("값이 같을 때는 같은 객체")
    void equalsLottoNumber() {
        LottoNumber one = LottoNumber.from(3);
        LottoNumber two = LottoNumber.from(3);

        assertEquals(one, two);
    }

    @Test
    @DisplayName("LottoNumber를 오름차순으로 정렬")
    void sortLottoNumbersInAscendingOrder() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        lottoNumbers.add(LottoNumber.from(2));
        lottoNumbers.add(LottoNumber.from(1));
        lottoNumbers.add(LottoNumber.from(3));

        Collections.sort(lottoNumbers);

        assertEquals(LottoNumber.from(1), lottoNumbers.get(0));
        assertEquals(LottoNumber.from(2), lottoNumbers.get(1));
        assertEquals(LottoNumber.from(3), lottoNumbers.get(2));
    }
}
