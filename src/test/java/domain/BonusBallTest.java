package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import util.Errors;

class BonusBallTest {

    @ParameterizedTest(name = "당첨숫자가 [2, 3, 4, 5, 6, 7] 일때, {0}을 넣어서 보너스 볼을 만들 수 있다.")
    @ValueSource(ints = {1, 10, 40, 45})
    @DisplayName("보너스 숫자를 만들 수 있다.")
    void bonusBallTest(int bonusBallNumber) {
        // given
        final Lotto winningLotto = Lotto.from(Arrays.asList(2, 3, 4, 5, 6, 7));
        // when
        final BonusBall bonusBall = BonusBall.createIfNotInList(bonusBallNumber, winningLotto);
        // then
        assertThat(bonusBall.getValue())
            .isEqualTo(bonusBallNumber);
    }

    private static Stream<Arguments> methodSourceOfCreateBonusBall() {
        return Stream.of(
            Arguments.arguments(Arrays.asList(1, 2, 3, 4, 5, 6), 6),
            Arguments.arguments(Arrays.asList(10, 15, 18, 20, 22, 24), 24)
        );
    }

    @ParameterizedTest(name = "당첨 숫자가 {0}일 때, 보너스 번호로 {1}이 들어오면 중복 에러가 난다.")
    @MethodSource("methodSourceOfCreateBonusBall")
    @DisplayName("로또번호와 보너스 번호는 중복될 수 없다.")
    void invalidWinningLottoTest(List<Integer> winningLottoNumbers, int bonusBallNumber) {
        // given
        final Lotto winningLotto = Lotto.from(winningLottoNumbers);
        // when
        // then
        assertThatThrownBy(() -> BonusBall.createIfNotInList(bonusBallNumber, winningLotto))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.BONUS_NUMBER_IS_IN_LOTTO_NUMBER);
    }
}
