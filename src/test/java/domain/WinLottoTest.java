package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static fixture.LottoFixture.testLottoFortyToFortyFive;
import static fixture.LottoFixture.testLottoOneToSix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WinLottoTest {

    private final Lotto lotto = testLottoOneToSix;

    @Test
    @DisplayName("OK : 당첨 번호를 생성한다.")
    void newWinLotto() {
        WinLotto.of(lotto, new LottoNumber(7));
    }

    @Test
    @DisplayName("ERROR : 당첨 번호와 보너스 번호가 동일하면 에러가 발생한다.")
    void newWinLottoSameBonusNumber() {
        assertThrows(IllegalArgumentException.class, () -> WinLotto.of(lotto, new LottoNumber(6)));
    }

    @ParameterizedTest
    @MethodSource("provideComparedLottoAndLottoRank")
    @DisplayName("OK : 로또 번호를 비교하여 로또 결과를 반환한다.")
    void calculateRank(Lotto comparedLotto, LottoRank expectedRank) {
        WinLotto winLotto = WinLotto.of(lotto, new LottoNumber(7));

        assertThat(winLotto.calculateRank(comparedLotto)).isEqualTo(expectedRank);
    }

    private static Stream<Arguments> provideComparedLottoAndLottoRank() {
        return Stream.of(
                Arguments.of(testLottoOneToSix, LottoRank.FIRST_PLACE),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)), LottoRank.SECOND_PLACE),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 8)), LottoRank.THIRD_PLACE),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 8, 9)), LottoRank.FOURTH_PLACE),
                Arguments.of(new Lotto(List.of(1, 2, 3, 8, 9, 10)), LottoRank.FIFTH_PLACE),
                Arguments.of(new Lotto(List.of(1, 2, 8, 9, 10, 11)), LottoRank.NO_PLACE),
                Arguments.of(testLottoFortyToFortyFive, LottoRank.NO_PLACE)
        );
    }
}