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
        // given
        int from = 1;
        int to = 10;

        // when
        NumberPool pool = NumberPool.of(from, to);

        // then
        assertThat(pool.getNumbers()).hasSize(to - from + 1);
    }

    @Test
    void from은_to보다_클_수_없다() {
        // given
        int from = 2;
        int to = 1;
        String expectedMessage = "from 값은 to 값보다 작아야 합니다.";

        // when and then
        assertThatThrownBy(() -> NumberPool.of(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    void 범위의_크기는_LOTTO_SIZE보다_커야_한다() {
        // given
        int from  = Lotto.LOTTO_NUMBER_MIN;
        int to = Lotto.LOTTO_NUMBER_MIN + Lotto.LOTTO_SIZE - 2;
        String expectedMessage = "범위의 크기는 " + Lotto.LOTTO_SIZE + " 보다 커야 합니다.";

        // when and then
        assertThatThrownBy(() -> NumberPool.of(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedMessage);
    }
}
