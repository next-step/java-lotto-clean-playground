package io.suhan.lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
public class NumberPoolTest {
    @Test
    void 특정_범위를_가진_Pool을_생성할_수_있다() {
        NumberPool pool = new NumberPool(1, 10);

        assertThat(pool.getNumbers()).hasSize(10);
    }

    @Test
    void from은_to보다_클_수_없다() {
        assertThrows(IllegalArgumentException.class, () -> new NumberPool(2, 1));
    }
}
