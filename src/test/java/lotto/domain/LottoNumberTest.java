package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    @DisplayName("로또 번호가 45를 초과하면 에러 발생")
    void 로또_번호가_45를_초과하면_에러_발생() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumber(46);
        });
    }

    @Test
    @DisplayName("로또 번호가 45를 초과하면 에러 발생")
    void 로또_번호가_1_미만이면_에러_발생() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumber(0);
        });
    }
}
