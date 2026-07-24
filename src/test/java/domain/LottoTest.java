package domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTest {

    @Test
    void 번호가_6개이면_생성된다() {
        assertDoesNotThrow(() -> createLotto(1, 2, 3, 4, 5, 6));
    }

    @Test
    void 번호가_6개보다_적으면_생성되지_않는다() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createLotto(1, 2, 3, 4, 5)
        );
        assertEquals("로또 번호는 6개여야 합니다.", exception.getMessage());
    }

    @Test
    void 번호가_6개보다_많으면_생성되지_않는다() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createLotto(1, 2, 3, 4, 5, 6, 7)
        );
        assertEquals("로또 번호는 6개여야 합니다.", exception.getMessage());
    }

    @Test
    void 번호가_정상범위인_경우_생성된다() {
        assertDoesNotThrow(() -> createLotto(1, 7, 16, 24, 32, 45));
    }

    @Test
    void 번호가_1보다_작으면_생성되지_않는다() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createLotto(0, 1, 2, 3, 4, 5)
        );
        assertEquals("로또 번호는 1~45 사이여야 합니다.", exception.getMessage());
    }

    @Test
    void 번호가_45보다_크면_생성되지_않는다() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createLotto(1, 2, 3, 4, 5, 46)
        );
        assertEquals("로또 번호는 1~45 사이여야 합니다.", exception.getMessage());
    }

    @Test
    void 중복된_숫자가_존재하면_생성되지_않는다() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createLotto(1, 2, 3, 4, 5, 5)
        );
        assertEquals("로또 번호는 중복될 수 없습니다.", exception.getMessage());
    }

    @Test
    void 번호가_모두_일치하면_6을_반환() {
        Lotto lotto1 = createLotto(1, 2, 3, 4, 5, 6);
        Lotto lotto2 = createLotto(1, 2, 3, 4, 5, 6);

        int result = lotto1.matchCount(lotto2);

        assertEquals(6, result);
    }

    @Test
    void 번호가_3개_일치하면_3을_반환() {
        Lotto lotto1 = createLotto(1, 2, 3, 4, 5, 6);
        Lotto lotto2 = createLotto(1, 2, 3, 7, 8, 9);

        int result = lotto1.matchCount(lotto2);

        assertEquals(3, result);
    }

    @Test
    void 일치하는_번호가_없으면_0을_반환() {
        Lotto lotto1 = createLotto(1, 2, 3, 4, 5, 6);
        Lotto lotto2 = createLotto(7, 8, 9, 10, 11, 12);

        int result = lotto1.matchCount(lotto2);

        assertEquals(0, result);
    }

    private Lotto createLotto(int... numbers) {
        return new Lotto(
                Arrays.stream(numbers)
                        .mapToObj(LottoNumber::new)
                        .toList()
        );
    }
}
