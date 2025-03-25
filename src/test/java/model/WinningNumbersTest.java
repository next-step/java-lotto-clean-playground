package model;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersTest {

    @Test
    void 로또_번호가_6개가_아니면_예외가_발생해야_한다() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4);
        assertThatThrownBy(() -> WinningNumbers.create(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6자리 입니다!");
    }

    @Test
    void 로또_번호가_중복되면_예외가_발생해야_한다() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 5);
        assertThatThrownBy(() -> WinningNumbers.create(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복이 없어야 합니다!");
    }

    @Test
    void 로또_번호가_1이상_45이하의_숫자가_아니면_예외가_발생해야_한다() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 46);
        assertThatThrownBy(() -> WinningNumbers.create(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1이상 45이하의 정수입니다!");
    }

}
