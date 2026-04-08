package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @DisplayName("보너스 볼이 당첨 번호 6개와 중복되면 예외가 발생한다.")
    @Test
    void duplicateBonusTest() {
        Lotto winningNumbers = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        LottoNumber bonusNumber = new LottoNumber(6); // 6번 중복!

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복될 수 없습니다");
    }

    @DisplayName("사용자의 로또를 받아 5개 일치 + 보너스 일치 시 2등(SECOND)을 반환한다.")
    @Test
    void matchSecondRankTest() {
        Lotto winningNumbers = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // 사용자가 1, 2, 3, 4, 5, 7을 찍음 (5개 일치, 보너스 7 일치)
        Lotto userLotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(7)
        ));

        Rank result = winningLotto.match(userLotto);
        assertThat(result).isEqualTo(Rank.SECOND);
    }
}