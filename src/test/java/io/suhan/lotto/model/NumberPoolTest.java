package io.suhan.lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import io.suhan.lotto.model.lotto.Lotto;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
public class NumberPoolTest {
    @Test
    void 특정_범위를_가진_Pool을_생성할_수_있다() {
        NumberPool pool = NumberPool.of(1, 10);

        assertThat(pool.getNumbers()).hasSize(10);
    }

    @Test
    void from은_to보다_클_수_없다() {
        String expectedMessage = "from 값은 to 값보다 작아야 합니다.";

        assertThatThrownBy(() -> NumberPool.of(2, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    void 범위의_크기는_LOTTO_SIZE보다_커야_한다() {
        String expectedMessage = "범위의 크기는 " + Lotto.LOTTO_SIZE + " 보다 커야 합니다.";

        assertThatThrownBy(() -> NumberPool.of(Lotto.LOTTO_NUMBER_MIN, Lotto.LOTTO_NUMBER_MIN + Lotto.LOTTO_SIZE - 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedMessage);
    }
}
