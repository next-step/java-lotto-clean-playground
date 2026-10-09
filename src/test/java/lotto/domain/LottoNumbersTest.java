package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

    @Test
    @DisplayName("로또 번호는 6개여야 한다")
    void 로또_번호는_6개여야_한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumbers(List.of(1, 2, 3, 4, 5));
        });
    }

    @Test
    @DisplayName("로또 번호는 중복될 수 없다")
    void 로또_번호는_중복될_수_없다() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumbers(List.of(1, 2, 3, 4, 5, 5));
        });
    }
}
