package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void createLottoWithInvalidSize() {
        assertThatThrownBy(
                () -> new Lotto(List.of(1, 2, 3, 4, 5))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLottoWithDuplicateNumbers() {
        assertThatThrownBy(
                () -> new Lotto(List.of(1, 2, 3, 4, 5, 5))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
