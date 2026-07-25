import domain.Lotto;
import domain.LottoNumber;
import domain.Rank;
import domain.WinningLotto;
import domain.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    private final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
    private final WinningLotto winningLotto = new WinningLotto(winningNumbers, new LottoNumber(7));

    @Test
    @DisplayName("5개 일치 + 보너스 일치면 SECOND 반환 테스트")
    void 다섯개와_보너스_일치_SECOND() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Rank expectedRank = Rank.SECOND;

        assertThat(winningLotto.match(lotto)).isEqualTo(expectedRank);
    }

    @Test
    @DisplayName("5개 일치 + 보너스 미일치면 THIRD 반환 테스트")
    void 다섯개_일치_보너스_미일치_THIRD() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 45));
        Rank expectedRank = Rank.THIRD;

        assertThat(winningLotto.match(lotto)).isEqualTo(expectedRank);
    }

    @Test
    @DisplayName("6개 일치면 FIRST 반환 테스트")
    void 여섯개_일치_FIRST() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Rank expectedRank = Rank.FIRST;

        assertThat(winningLotto.match(lotto)).isEqualTo(expectedRank);
    }

    @Test
    @DisplayName("보너스 볼이 당첨 번호와 중복되면 예외 발생 테스트")
    void 보너스가_당첨번호와_중복되면_예외() {
        String throwMessage = "보너스 볼은 당첨 번호와 중복될 수 없습니다.";

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, new LottoNumber(6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(throwMessage);
    }
}
