package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.Errors;

class BonusBallTest {

    @ParameterizedTest(name = "{0}이 보너스볼로 들어오면 1~45 사이의 숫자가 아니므로 예외가 발생한다.")
    @ValueSource(ints = {-1, 0, 46, 100})
    @DisplayName("보너스 숫자는 1~45 사이의 숫자이다.")
    void invalidBonusBallTest(int bonusBallNumber) {
        // given
        // when
        // then
        assertThatThrownBy(() -> new BonusBall(bonusBallNumber))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.NUMBER_IS_NOT_IN_VALID_RANGE);
    }

    @ParameterizedTest(name = "{0}을 넣어서 보너스 볼을 만들 수 있다.")
    @ValueSource(ints = {1, 10, 40, 45})
    @DisplayName("보너스 숫자를 만들 수 있다.")
    void bonusBallTest(int bonusBallNumber) {
        // given
        // when
        final BonusBall bonusBall = new BonusBall(bonusBallNumber);
        // then
        assertThat(bonusBall.number())
            .isEqualTo(bonusBallNumber);
    }
}
