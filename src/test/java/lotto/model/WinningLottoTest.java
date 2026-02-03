package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import lotto.domain.model.Lotto;
import lotto.domain.model.LottoNumber;
import lotto.domain.model.LottoRank;
import lotto.domain.model.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    void constructor_ShouldThrowException_WhenBonusNumberIsDuplicated() {
        // given
        Lotto winningNumbers = Lotto.from(createLottoNumbers(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(1); // 중복된 번호

        // when & then
        assertThatThrownBy(() -> WinningLotto.of(winningNumbers, bonusNumber))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("보너스 볼 번호는 기존 로또 번호와 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어의 로또와 당첨 번호를 비교하여 올바른 등수를 판정한다")
    void judge_ShouldReturnCorrectRank() {
        // given
        Lotto winningNumbers = Lotto.from(createLottoNumbers(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusNumber);

        Lotto playerLotto = Lotto.from(createLottoNumbers(1, 2, 3, 4, 5, 7)); // 5개 일치 + 보너스 일치

        // when
        LottoRank result = winningLotto.judge(playerLotto);

        // then
        assertThat(result).isEqualTo(LottoRank.SECOND);
    }

    private List<LottoNumber> createLottoNumbers(int... numbers) {
        return Arrays.stream(numbers)
            .mapToObj(LottoNumber::valueOf)
            .toList();
    }

}
