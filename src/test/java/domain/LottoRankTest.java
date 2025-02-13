package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @ParameterizedTest
    @MethodSource("provideLottoResult")
    @DisplayName("OK : 당첨 번호에 따른 당첨 결과를 반환한다.")
    void resultToLottoRank(int matchedCount, boolean isBonusNumber, LottoRank lottoRank) {
        assertThat(LottoRank.of(matchedCount, isBonusNumber)).isEqualTo(lottoRank);
    }

    private static Stream<Arguments> provideLottoResult() {
        return Stream.of(
                Arguments.of(0, false,  LottoRank.NO_PLACE),
                Arguments.of(1, false,  LottoRank.NO_PLACE),
                Arguments.of(1, true,  LottoRank.NO_PLACE),
                Arguments.of(2, false,  LottoRank.NO_PLACE),
                Arguments.of(2, true,  LottoRank.NO_PLACE),
                Arguments.of(3, false,  LottoRank.FIFTH_PLACE),
                Arguments.of(3, true,  LottoRank.FIFTH_PLACE),
                Arguments.of(4, false,  LottoRank.FOURTH_PLACE),
                Arguments.of(4, true,  LottoRank.FOURTH_PLACE),
                Arguments.of(5, false,  LottoRank.THIRD_PLACE),
                Arguments.of(5, true,  LottoRank.SECOND_PLACE),
                Arguments.of(6, false,  LottoRank.FIRST_PLACE),
                Arguments.of(6, true,  LottoRank.FIRST_PLACE)
        );
    }
}