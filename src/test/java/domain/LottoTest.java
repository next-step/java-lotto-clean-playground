package domain;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


class LottoTest {
    private Lotto createLotto(int... numbers) {
        return new Lotto(
                Arrays.stream(numbers)
                        .mapToObj(LottoNumber::new)
                        .toList()
        );
    }

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
    void 중복된_숫자가_존재하면_생성되지_않는다() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createLotto(1, 2, 3, 4, 5, 5)
        );
        assertEquals("로또 번호는 중복될 수 없습니다.", exception.getMessage());
    }

    @Test
    void 번호가_모두_일치하면_6을_반환() {
        // Given
        Lotto lotto1 = createLotto(1, 2, 3, 4, 5, 6);
        Lotto lotto2 = createLotto(1, 2, 3, 4, 5, 6);

        // When
        int result = lotto1.matchCount(lotto2);

        // Then
        assertEquals(6, result);
    }

    @Test
    void 번호가_3개_일치하면_3을_반환() {
        // Given
        Lotto lotto1 = createLotto(1, 2, 3, 4, 5, 6);
        Lotto lotto2 = createLotto(1, 2, 3, 7, 8, 9);

        // When
        int result = lotto1.matchCount(lotto2);

        // Then
        assertEquals(3, result);
    }

    @Test
    void 일치하는_번호가_없으면_0을_반환() {
        // Given
        Lotto lotto1 = createLotto(1, 2, 3, 4, 5, 6);
        Lotto lotto2 = createLotto(7, 8, 9, 10, 11, 12);

        // When
        int result = lotto1.matchCount(lotto2);

        // Then
        assertEquals(0, result);
    }

    @Test
    void 보너스_번호와_당첨_번호가_중복되면_오류가_발생한다() {
        // Given
        Lotto lotto = createLotto(1, 2, 3, 4, 5, 6);

        // When
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> lotto.validateBonusNumber(new LottoNumber(1))
                );
        // Then
        assertThat(exception.getMessage())
                .isEqualTo("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    void 이미_포함된_번호를_확인하면_true를_반환한다() {
        // Given
        Lotto lotto = createLotto(1, 2, 3, 4, 5, 6);

        // When
        boolean result = lotto.contains(new LottoNumber(1));

        // Then
        assertTrue(result);
    }

    @Test
    void 포함되지_않은_번호를_확인하면_false를_반환한다() {
        // Given
        Lotto lotto = createLotto(1, 2, 3, 4, 5, 6);

        // When
        boolean result = lotto.contains(new LottoNumber(7));

        // Then
        assertFalse(result);
    }
}
