package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ManualCountTest {

    @Test
    void 음수입력시_예외발생() {
        Assertions.assertThatThrownBy(() -> new ManualCount(10, -1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 총개수보다_넘게입력시_예외발생() {
        Assertions.assertThatThrownBy(() -> new ManualCount(10, 11))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
