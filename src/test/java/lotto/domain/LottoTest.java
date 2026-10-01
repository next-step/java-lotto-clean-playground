package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("당첨 번호와 일치하는 번호의 개수를 정확히 센다")
    void 당첨번호와_일치하는_번호의_개수를_정확히_센다() {
        WinningNumbers winningNumbers =
                new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 10);

        Lotto lotto = new Lotto(List.of(1, 2, 3, 7, 8, 9));

        int matchCount = lotto.countMatch(winningNumbers);

        assertThat(matchCount).isEqualTo(3);
    }

    @Test
    @DisplayName("당첨 번호와 일치하는 번호가 없으면 0을 반환한다")
    void 당첨번호와_일치하는_번호가_없으면_0을_반환한다() {
        WinningNumbers winningNumbers =
                new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 10);

        Lotto lotto = new Lotto(List.of(14, 15, 16, 17, 18, 19));

        int matchCount = lotto.countMatch(winningNumbers);

        assertThat(matchCount).isEqualTo(0);
    }

}
