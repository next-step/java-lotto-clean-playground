package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThatCode;

public class LottoGeneratorTest {

    @Test
    @DisplayName("유효한 로또 한 장을 생성한다")
    void generateLotto() {
        assertThatCode(LottoGenerator::generateLotto)
                .doesNotThrowAnyException();
    }
}
