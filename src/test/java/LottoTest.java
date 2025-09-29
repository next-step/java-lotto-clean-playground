import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import domain.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//실패코드 작성
public class LottoTest {
    @Test
    @DisplayName("로또 숫자가 정렬이 아닐 경우 예외가 발생한다")
    void testLottoSort() {
        List<LottoNumber> unsorted = Arrays.asList(new LottoNumber(3), new LottoNumber(1), new LottoNumber(2), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Lotto(unsorted));
        assertEquals("로또 숫자는 정렬되어야 합니다.", e.getMessage());

    }

    @Test
    @DisplayName("로또 숫자가 1~45 사이의 숫자가 아닐 경우 예외가 발생한다")
    void testLottoRange() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            List<LottoNumber> outOfRange = Arrays.asList(new LottoNumber(0), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
            new Lotto(outOfRange);
        });
        assertEquals("로또 번호는 1부터 45 사이의 숫자여야 합니다.", e.getMessage());
    }

    @Test
    @DisplayName("로또 숫자가 중복되는 경우 예외가 발생한다")
    void testLottoDuplicate() {
        List<LottoNumber> duplicated = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(2), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Lotto(duplicated));
        assertEquals("로또 숫자는 중복될 수 없습니다.", e.getMessage());
    }
}
