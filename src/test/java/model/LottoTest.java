package model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class LottoTest {

    private Lotto lotto = Lotto.create(new LottoNumbersGenerator().generate());

    @Test
    void 발급한_로또_번호는_6개의_숫자로_이루어진다() {
        assertThat(lotto.size()).isEqualTo(6);
    }

    @Test
    void 발급한_로또_번호들은_서로_달라야_한다() {
        Set<Integer> numbers = lotto.getNumbers();
        long count = numbers.stream()
                .distinct()
                .count();

        assertThat(count).isEqualTo(6);
    }

    @Test
    void getLotto를_통해_얻은_객체를_외부에서_변경해도_원상태는_유지되어야_한다() {
        lotto.getNumbers().add(10);
        assertThat(lotto.getNumbers().size()).isEqualTo(6);
    }

    @Test
    void 로또_번호가_6개가_아니면_예외가_발생해야_한다() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4);
        assertThatThrownBy(() -> Lotto.create(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6자리 입니다!");
    }

    @Test
    void 로또_번호가_중복되면_예외가_발생해야_한다() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 5);
        assertThatThrownBy(() -> Lotto.create(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복이 없어야 합니다!");
    }

    @Test
    void 로또_번호가_1이상_45이하의_숫자가_아니면_예외가_발생해야_한다() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 46);
        assertThatThrownBy(() -> Lotto.create(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1이상 45이하의 정수입니다!");
    }
}
