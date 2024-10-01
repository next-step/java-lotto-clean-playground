import domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@DisplayName("로또 당첨 업데이트 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UpdateWinningLottosTest {

    @DisplayName("로또 당첨 시 당첨 로또목록을 업데이트 한다.")
    @ParameterizedTest
    @MethodSource("generateData")
    public void updateWinningLottos(final List<Integer> lottoNumber, final int correctCount, final boolean isSecondPrize, final WinningLottos winningLotto, final int expected) {
        //given
        final Lotto lotto = new Lotto(lottoNumber);
        Lottos lottos = new Lottos(new ArrayList<Lotto>(List.of(lotto)));

        final List<Integer> winnerLottoNumber = List.of(1, 2, 3, 4, 5, 6);
        final LastWeekWinningLotto lastWeekWinningLotto = new LastWeekWinningLotto(winnerLottoNumber, 44);

        UpdateWinningLottos updateWinningLottos = new UpdateWinningLottos(new CorrectLottoNumbersCheck());

        //when
        updateWinningLottos.updateWinningLottos(lottos, lastWeekWinningLotto);

        //then
        Assertions.assertThat(WinningLottos.of(correctCount, isSecondPrize).getLottoCount()).isEqualTo(expected);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(Arrays.asList(2, 4, 6, 10, 11, 12), 3, false, WinningLottos.THREE_CORRECT_LOTTOS, 1),
                Arguments.of(Arrays.asList(2, 3, 5, 6, 11, 12), 4, false, WinningLottos.FOUR_CORRECT_LOTTOS, 1),
                Arguments.of(Arrays.asList(2, 3, 4, 5, 6, 12), 5, false, WinningLottos.FIVE_CORRECT_LOTTOS, 1),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 44), 5, true, WinningLottos.FIVE_AND_BONUS_CORRECT_LOTTOS, 1),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 6, false, WinningLottos.SIX_CORRECT_LOTTOS, 1)
        );
    }
}
