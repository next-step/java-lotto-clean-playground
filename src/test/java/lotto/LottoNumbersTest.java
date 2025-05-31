package lotto;

import lotto.model.LottoNumbers;
import lotto.model.MatchCount;
import lotto.model.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumbersTest {

    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외를 발생")
    void throwExceptionIfLottoNumbersAreNotSix() {
        assertThrows(IllegalArgumentException.class, () ->
            new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5))
        );
    }

    @Test
    @DisplayName("로또 번호와 당첨 번호를 비교해 일치 개수를 계산")
    void calculateMatchedCountCorrectly() {
        LottoNumbers lotto = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningNumbers winning = new WinningNumbers(Arrays.asList(1, 2, 3, 7, 8, 9));

        MatchCount result = lotto.match(winning);
        assertThat(result.getCount()).isEqualTo(3);
    }
}
