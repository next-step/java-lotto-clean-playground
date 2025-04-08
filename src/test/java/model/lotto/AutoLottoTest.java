package model.lotto;

import model.LottoNumber;
import model.LottoNumbersGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AutoLottoTest {

    private AutoLotto autoLotto;

    @BeforeEach
    void setUp() {
        autoLotto = AutoLotto.of(new LottoNumbersGenerator().generate());
    }

    @Test
    void 자동_발급한_로또_번호는_6개의_숫자로_이루어진다() {
        assertThat(autoLotto.size()).isEqualTo(6);
    }

    @Test
    void 자동_발급한_로또_번호들은_서로_달라야_한다() {
        Set<LottoNumber> numbers = autoLotto.getNumbers();
        long count = numbers.stream()
                .distinct()
                .count();
        assertThat(count).isEqualTo(6);
    }
}
