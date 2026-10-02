package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {

    @Test
    @DisplayName("당첨 번호와 보너스 번호는 중복될 수 없다")
    void 당첨_번호와_보너스_번호는_중복될_수_없다() {
        LottoNumbers numbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));

        assertThrows(IllegalArgumentException.class, () -> {
            new WinningNumbers(numbers, new LottoNumber(1));
        });
    }

}
