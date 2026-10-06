package domain;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    void returnsSortedNumbers() {
        // 준비
        Lotto lotto = new Lotto(List.of(6, 2, 4, 1, 5, 3));
        // 검증
        assertThat(lotto.getSortedNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void rejectsDuplicateNumbers() {
        // 실행 및 검증
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectsWrongNumberCount() {
        // 실행 및 검증
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void comparesSeparateNumberObjectsByValue() {
        // 준비
        Lotto lotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        Lotto winner = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        // 검증
        assertThat(lotto.countMatches(winner)).isEqualTo(3);
        assertThat(lotto.contains(new LottoNumber(10))).isTrue();
    }
}
