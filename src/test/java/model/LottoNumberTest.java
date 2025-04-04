package model;


import model.lotto.Lotto;
import model.lotto.ManualLotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static utils.LottoConstants.LOTTO_MAX_NUMBER;
import static utils.LottoConstants.LOTTO_MIN_NUMBER;

class LottoNumberTest {

    @Test
    void 로또숫자가_0이면_예외가_발생해야_한다() {
        assertThatThrownBy(() -> LottoNumber.from(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 " + LOTTO_MIN_NUMBER + "이상 " + LOTTO_MAX_NUMBER + "이하의 정수입니다!");
    }

    @Test
    void 로또숫자가_46이면_예외가_발생해야_한다() {
        assertThatThrownBy(() -> LottoNumber.from(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 " + LOTTO_MIN_NUMBER + "이상 " + LOTTO_MAX_NUMBER + "이하의 정수입니다!");
    }

    @Test
    void 보너스_번호가_1이상_45이하의_정수가_아니면_예외가_발생해야_한다() {
        int bonusNumber = -1;
        Lotto winningNumbers = ManualLotto.of(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> LottoNumber.bonusNumber(bonusNumber, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 " + LOTTO_MIN_NUMBER + "이상 " + LOTTO_MAX_NUMBER + "이하의 정수입니다!");
    }

    @Test
    void 보너스_번호가_추첨_번호와_중복되는_값을_가지면_예외가_발생해야_한다() {
        int bonusNumber = 1;
        Lotto winningNumbers = ManualLotto.of(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> LottoNumber.bonusNumber(bonusNumber, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복되면 안 됩니다!");
    }
}
