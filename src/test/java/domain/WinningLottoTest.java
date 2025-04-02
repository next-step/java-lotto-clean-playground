package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 번호와 보너스 번호가 정상적으로 저장되어야 한다")
    void create_WinningLotto_Success_Test() {
        Lotto lotto = new Lotto(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
        LottoNumber bonus = new LottoNumber(7);

        WinningLotto winningLotto = new WinningLotto(lotto, bonus);

        assertThat(winningLotto.getNumbers()).containsExactly(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );
        assertThat(winningLotto.getBonusNumber()).isEqualTo(new LottoNumber(7));
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생해야 한다")
    void Duplicate_BonusNumber_ExceptionThrown() {
        Lotto lotto = new Lotto(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
        LottoNumber duplicateBonus = new LottoNumber(6); // 당첨 번호와 중복

        assertThatThrownBy(() -> new WinningLotto(lotto, duplicateBonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("당첨 번호는 오름차순 정렬되어 반환되어야 한다")
    void WinningNumbers_Sort_Test() {
        Lotto lotto = new Lotto(Arrays.asList(
                new LottoNumber(5),
                new LottoNumber(3),
                new LottoNumber(1),
                new LottoNumber(6),
                new LottoNumber(2),
                new LottoNumber(4)
        ));
        LottoNumber bonus = new LottoNumber(7);

        WinningLotto winningLotto = new WinningLotto(lotto, bonus);

        List<LottoNumber> expectedSortedNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        assertThat(winningLotto.getNumbers()).isEqualTo(expectedSortedNumbers);
    }

    @Test
    @DisplayName("보너스 번호가 정확히 반환되어야 한다")
    void get_BonusNumber_Test() {
        Lotto lotto = new Lotto(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
        LottoNumber bonus = new LottoNumber(9);

        WinningLotto winningLotto = new WinningLotto(lotto, bonus);

        assertThat(winningLotto.getBonusNumber()).isEqualTo(new LottoNumber(9));
    }
}
