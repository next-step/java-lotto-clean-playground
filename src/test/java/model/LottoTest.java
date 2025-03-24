package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoTest {

    private Lotto lotto = new Lotto(new LottoNumberGenerator());

    @Test
    void 발급한_로또_번호는_6개의_숫자로_이루어진다() {
        assertThat(lotto.size()).isEqualTo(6);
    }

    @Test
    void 발급한_로또_번호들은_서로_달라야_한다() {
        List<Integer> numbers = lotto.getNumbers();
        long count = numbers.stream()
                .distinct()
                .count();

        assertThat(count).isEqualTo(6);
    }

    @Test
    void getLotto를_통해_얻은_객체를_변경하면_예외가_발생해야_한다() {
        assertThatThrownBy(() -> lotto.getNumbers().add(1))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
