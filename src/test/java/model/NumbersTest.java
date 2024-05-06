package model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumbersTest {

    @Test
    @DisplayName("로또에 사용되는 숫자들은 서로 중복되어선 안된다.")
    void validateCommonElementsTest() {
        final List<Integer> hasCommonElements = List.of(1, 2, 3, 3, 4, 5);
        assertThatThrownBy(() -> new Numbers(hasCommonElements))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자는 서로 중복되어선 안됩니다.");
    }

    @Test
    @DisplayName("로또에 사용되는 숫자는 6개여야 한다.")
    void validateNumbersSizeTest() {
        final List<Integer> elements = List.of(1, 2, 3, 4, 5, 6);
        assertThatCode(() -> new Numbers(elements)).doesNotThrowAnyException();
    }

}
