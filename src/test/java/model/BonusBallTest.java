package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BonusBallTest {

    @Testa
    public void 보너스볼은_당첨번호와_중복이되면_예외가_발생해야_한다() {
        Lotto winningNumbers = Lotto.create(List.of(1, 2, 3, 4, 5, 6));
        int bonusBallNumber = 1;

        Assertions.assertThatThrownBy(() -> BonusBall.of(bonusBallNumber, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복되면 안 됩니다!");
    }
}
