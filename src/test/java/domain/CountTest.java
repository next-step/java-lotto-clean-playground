package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CountTest {
    @DisplayName("개수가 0 이상이면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 30})
    public void testCount_ValidCount(int count) {
        // when & then
        assertThatCode(() -> new Count(count)).doesNotThrowAnyException();
    }

    @DisplayName("개수가 음수면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -20})
    public void testCount_InvalidCount(int count) {
        // when & then
        assertThatThrownBy(() -> new Count(count)).isInstanceOf(IllegalArgumentException.class).hasMessage("개수가 음수일 수 없습니다.");
    }
}
