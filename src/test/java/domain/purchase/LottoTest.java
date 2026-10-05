package domain.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @Test
    @DisplayName("로또에 있는 번호면 contains는 true를 반환한다.")
    void containsReturnsTrueWhenNumberExists() {
        // given
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

        // when
        boolean result = lotto.contains(new LottoNumber(3));

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("로또에 없는 번호면 contains는 false를 반환한다.")
    void containsReturnsFalseWhenNumberNotExists() {
        // given
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

        // when
        boolean result = lotto.contains(new LottoNumber(7));

        // then
        assertFalse(result);
    }

    @Test
    @DisplayName("로또 번호가 6개가 아니면 오류가 발생한다.")
    void errorWhenSizeIsNotSix() {
        // when & then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Lotto.from(List.of(1, 2, 3, 4, 5)));
        assertEquals("로또 번호는 6개여야 합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("로또 번호에 중복이 있으면 오류가 발생한다.")
    void errorWhenNumbersDuplicated() {
        // when & then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Lotto.from(List.of(1, 1, 2, 3, 4, 5)));
        assertEquals("로또 번호는 중복될 수 없습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("생성 후 원본 리스트를 수정해도 로또는 바뀌지 않는다.")
    void notAffectedByOriginalListChange() {
        // given
        List<LottoNumber> numbers = new ArrayList<>(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));
        Lotto lotto = new Lotto(numbers);

        // when
        numbers.add(new LottoNumber(7));

        // then
        assertEquals("[1, 2, 3, 4, 5, 6]", lotto.toString());
    }
}
