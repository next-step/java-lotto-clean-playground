package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusBallTest {

    @Test
    @DisplayName("bonus number is out of range")
    void exceptionRange() {
        assertThatThrownBy(() -> new BonusBall(46, List.of(1, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼은 1부터 45 사이의 숫자여야 합니다.");
    }

    @Test
    @DisplayName("bonus number duplicates winning numbers")
    void exceptionBonusDuplicated() {
        assertThatThrownBy(() -> new BonusBall(1, List.of(1, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
    }
}

