import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import domain.*;


import static org.junit.jupiter.api.Assertions.assertThrows;

//실패코드 작성
public class LottoTest {
    @Test
    @DisplayName("로또 숫자 정렬 확인")
    void testLottoSort() {
        List<Integer> unsorted = Arrays.asList(5, 1, 3, 2, 4, 6);
        assertThrows(IllegalArgumentException.class, () -> new Lotto(unsorted));

    }

    @Test
    @DisplayName("로또 숫자가 1~45 사이의 숫자인지 확인")
    void testLottoRange() {
        List<Integer> outOfRange = Arrays.asList(0, 2, 3, 4, 5, 6);
        assertThrows(IllegalArgumentException.class, () -> new Lotto(outOfRange));
    }

    @Test
    @DisplayName("로또 숫자가 중복되지 않는지 확인")
    void testLottoDuplicate() {
        List<Integer> duplicated = Arrays.asList(1, 2, 3, 3, 4, 5);
        assertThrows(IllegalArgumentException.class, () -> new Lotto(duplicated));
    }
}
