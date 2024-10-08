import domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @DisplayName("로또_번호가_6개가_아니면_예외가_발생한다")
    @Test
    void 로또_번호가_6개가_아니면_예외가_발생한다() {
        List<Integer> Lottos = Arrays.asList(1, 2, 3);

        assertThatThrownBy(() -> new Lotto(Lottos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @DisplayName("로또_번호가_중복이_있으면_예외가_발생한다")
    @Test
    void 로또_번호가_중복이_있으면_예외가_발생한다() {
        List<Integer> Lottos = Arrays.asList(1, 1, 1, 1, 1, 1);

        assertThatThrownBy(() -> new Lotto(Lottos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복이 없어야 합니다.");
    }

    @DisplayName("로또_번호가_1부터_45사이가_아니면_예외가_발생한다")
    @Test
    void 로또_번호가_1부터_45사이가_아니면_예외가_발생한다() {
        List<Integer> Lottos = Arrays.asList(100, 200, 300, 400, 500, 600);

        assertThatThrownBy(() -> new Lotto(Lottos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1 ~ 45 사이의 자연수여야 합니다.");
    }
}
