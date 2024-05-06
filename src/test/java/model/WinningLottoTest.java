package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class WinningLottoTest {

    @ParameterizedTest
    @MethodSource("lottoAndWinningLotto")
    void 당첨_로또와_비교하여_순위를_결정한다(Lotto lotto, WinningLotto winningLotto, Rank expected) {
        // given & when
        final Rank actual = winningLotto.match(lotto);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> lottoAndWinningLotto() {
        return Stream.of(
                Arguments.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 7), Rank._1ST_PRIZE),
                Arguments.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), WinningLotto.from(List.of(1, 2, 3, 4, 5, 8), 6), Rank._2ND_PRIZE),
                Arguments.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), WinningLotto.from(List.of(1, 2, 3, 4, 5, 8), 9), Rank._3RD_PRIZE),
                Arguments.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), WinningLotto.from(List.of(1, 2, 3, 4, 7, 8), 9), Rank._4TH_PRIZE),
                Arguments.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), WinningLotto.from(List.of(1, 2, 3, 7, 8, 9), 10), Rank._5TH_PRIZE),
                Arguments.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), WinningLotto.from(List.of(1, 2, 7, 8, 9, 10), 11), Rank._LAST_PRIZE));
    }
}
