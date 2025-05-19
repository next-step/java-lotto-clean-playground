package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {
    private final LottoGenerator generator = new LottoGenerator();

    @Test
    @DisplayName("요청한 개수만큼 로또가 생성된다")
    void generate_correctNumberOfLottos() {
        int count = 5;
        List<Lotto> lottos = generator.generate(count);
        assertThat(lottos).hasSize(count);
    }

    @Test
    @DisplayName("생성된 각 로또는 6개의 번호를 가진다")
    void eachLottoHas6Numbers() {
        List<Lotto> lottos = generator.generate(10);
        for (Lotto lotto : lottos) {
            assertThat(lotto.getNumbers()).hasSize(6);
        }
    }

    @Test
    @DisplayName("로또 번호는 1~45 범위 안에 있어야 한다")
    void numbersWithinValidRange() {
        List<Lotto> lottos = generator.generate(10);
        for (Lotto lotto : lottos) {
            for (LottoNumber number : lotto.getNumbers()) {
                int value = Integer.parseInt(number.toString());
                assertThat(value).isBetween(1, 45);
            }
        }
    }
}
