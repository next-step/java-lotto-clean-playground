package domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.Errors;

class WinningLottoTest {

    @Test
    @DisplayName("당첨숫자(Lotto)와 보너스볼을 가진 winningLotto를 만들 수 있다.")
    void createTest() {
        //given
        final Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        final BonusBall bonusBall = new BonusBall(10);
        // when
        final WinningLotto winningLotto = new WinningLotto(lotto, bonusBall);
        // then
        assertThat(winningLotto.getWinningLottoNumbers())
            .containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(winningLotto.getBonusBallNumber())
            .isEqualTo(10);
    }

    private static Stream<Arguments> methodSourceOfCreateWinningLotto() {
        return Stream.of(
            Arguments.arguments(Arrays.asList(1, 2, 3, 4, 5, 6), 6),
            Arguments.arguments(Arrays.asList(10, 15, 18, 20, 22, 24), 24)
        );
    }

    @ParameterizedTest(name = "당첨 숫자가 {0}일 때, 보너스 번호로 {1}이 들어오면 중복 에러가 난다.")
    @MethodSource("methodSourceOfCreateWinningLotto")
    @DisplayName("로또번호와 보너스 번호는 중복될 수 없다.")
    void invalidWinningLottoTest(List<Integer> winningLottoNumbers, int bonusBallNumber) {
        // given
        final Lotto lotto = new Lotto(winningLottoNumbers);
        final BonusBall bonusBall = new BonusBall(bonusBallNumber);
        // when
        // then
        assertThatThrownBy(() -> new WinningLotto(lotto, bonusBall))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.BONUS_NUMBER_IS_IN_LOTTO_NUMBER);
    }

}
