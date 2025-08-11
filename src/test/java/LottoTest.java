import model.Lotto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class LottoTest {
    @Test
    void 로또_정상_생성() {
        List<Integer> numbers = List.of(5, 12, 23, 34, 41, 45);
        Lotto lotto = new Lotto(numbers);
        assertEquals(numbers, lotto.getNumbers());
    }

    @Test
    void 로또_번호가_6개_이하이면_예외가_발생한다() {
        List<Integer> numbers = List.of(1, 2, 3);
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> new Lotto(numbers)
        );
        assertTrue(ex.getMessage().contains("6개"));
    }

    @Test
    void 로또_번호가_6개_이상이면_예외가_발생한다(){
        List<Integer> numbers = List.of(5, 12, 23, 34, 41, 42, 43);
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> new Lotto(numbers)
        );
        assertTrue(ex.getMessage().contains("6개"));
    }

    @Test
    void 로또_번호가_1미만_45초과이면_예외가_발생한다() {
        List<Integer> numbers = List.of(0, 2, 3, 4, 5, 6);
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> new Lotto(numbers)
        );
        assertTrue(ex.getMessage().contains("1이상 45이하"));
    }
}
