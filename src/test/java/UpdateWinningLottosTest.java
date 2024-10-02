import domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
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

    @BeforeEach
    public void initializeWinningLotto() {
        WinningLottosStatus.initialize();
    }

    @DisplayName("로또 당첨 시 당첨 로또목록을 업데이트 한다.")
    @ParameterizedTest
    @MethodSource("generateData")
    public void updateWinningLottos(final List<Integer> passiveLottoNumbers, final List<Integer> autoLottoNumbers, final int correctCount, final boolean isSecondPrize, final int expected) {
        //given
        Lottos passiveLottos = new Lottos(new ArrayList<Lotto>(List.of(new Lotto(passiveLottoNumbers))));
        Lottos autoLottos = new Lottos(new ArrayList<Lotto>(List.of(new Lotto(autoLottoNumbers))));

        final List<Integer> winnerLottoNumber = List.of(1, 2, 3, 4, 5, 6);
        final WinningLotto lastWeekWinningLotto = new WinningLotto(winnerLottoNumber, 44);

        UpdateWinningLottos updateWinningLottos = new UpdateWinningLottos(new CorrectLottoNumbersCheck());

        //when
        updateWinningLottos.updateWinningLottos(passiveLottos, autoLottos, lastWeekWinningLotto);

        //then
        Assertions.assertThat(WinningLottosStatus.of(correctCount, isSecondPrize).getLottoCount()).isEqualTo(expected);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(Arrays.asList(2, 4, 6, 10, 11, 12), Arrays.asList(2, 4, 6, 10, 11, 12), 3, false, 2),
                Arguments.of(Arrays.asList(2, 3, 5, 6, 11, 12), Arrays.asList(2, 3, 5, 6, 11, 12), 4, false, 2),
                Arguments.of(Arrays.asList(2, 3, 4, 5, 6, 12), Arrays.asList(2, 3, 4, 5, 35, 12), 5, false, 1),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 44), Arrays.asList(1, 2, 3, 4, 5, 44), 5, true, 2),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(1, 2, 3, 4, 5, 35), 6, false, 1)
        );
    }
}
