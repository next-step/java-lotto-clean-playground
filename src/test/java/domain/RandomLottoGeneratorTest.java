package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomLottoGeneratorTest {
    @Test
    void 번호_6개를_가진_로또를_생성한다() {
        LottoGenerator generator = new RandomLottoGenerator();

        Lotto lotto = generator.generate();

        assertThat(lotto.getNumbers()).hasSize(6);
    }
}
