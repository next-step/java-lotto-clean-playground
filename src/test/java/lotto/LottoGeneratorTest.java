package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {

    @Test
    @DisplayName("유효한 로또 한 장을 생성한다")
    void generateLotto() {
        Lotto lotto = LottoGenerator.generateLotto();

        assertThat(lotto.countMatches(lotto)).isEqualTo(6);
    }
}
