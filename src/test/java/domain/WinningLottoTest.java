package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {
    @Test
    @DisplayName("보너스볼 매치 테스트")
    void isMatchBonusNumberTest() {
        // Given
        WinningLotto winningLotto = new WinningLotto(new Lotto(new LottoNumbers(
                List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4),
                        LottoNumber.of(5), LottoNumber.of(6)))), LottoNumber.of(7));

        Lotto lotto1 = new Lotto(new LottoNumbers(
                List.of(LottoNumber.of(11), LottoNumber.of(12), LottoNumber.of(13), LottoNumber.of(14),
                        LottoNumber.of(15), LottoNumber.of(7))));

        // When
        boolean isMatched = winningLotto.isMatchBonusNumber(lotto1);

        // Then
        assertThat(isMatched).isTrue();
    }
}
