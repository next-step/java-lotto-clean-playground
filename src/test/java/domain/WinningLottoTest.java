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
                List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)))), new LottoNumber(7));

        Lotto lotto1 = new Lotto(new LottoNumbers(
                List.of(new LottoNumber(11), new LottoNumber(12), new LottoNumber(13), new LottoNumber(14),
                        new LottoNumber(15), new LottoNumber(7))));

        // When
        boolean isMatched = winningLotto.isMatchBonusNumber(lotto1);

        // Then
        assertThat(isMatched).isTrue();
    }
}
