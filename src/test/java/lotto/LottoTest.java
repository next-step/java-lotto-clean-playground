package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.Lotto;
import lotto.generator.NumberGenerator;
import lotto.generator.RandomNumberGenerator;
import lotto.mock.Lotto7NumberGenerator;

class LottoTest {

    private final NumberGenerator numberGenerator = new RandomNumberGenerator();
    private final NumberGenerator numberGenerator7 = new Lotto7NumberGenerator();

    @Test
    @DisplayName("로또번호 개수 확인")
    void checkLottoCount6() {
        Lotto lotto = new Lotto(numberGenerator);
        assertThat(lotto.getLottoNums()).hasSize(6);
    }

    @Test
    @DisplayName("로또번호 개수가 6이 아니면 오류")
    void checkLottoCount() {
        assertThatThrownBy(() -> {
                Lotto lotto = new Lotto(numberGenerator7);
            }
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
