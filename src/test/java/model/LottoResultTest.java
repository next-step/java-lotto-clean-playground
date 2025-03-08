package model;

import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoResultTest {

    private LottoResult lottoResult;

    @BeforeEach
    void setUp() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        lottoResult = new LottoResult(winningNumbers, bonusNumber);  // 기본적으로 하나의 객체만 생성
    }

    @Test
    @DisplayName("로또 랭크에 대한 수익률을 정확히 계산하는 지 검증한다.")
    void should_CalculateEarningsRate() {
        List<LottoRank> lottoRanks = List.of(
                LottoRank.SIX_MATCHES,
                LottoRank.FIVE_MATCHES,
                LottoRank.NO_WINNER
        );

        double result = lottoResult.calculateEarningsRate(lottoRanks);

        double expectedRate = (double) (2_000_000_000 + 1_500_000) / (lottoRanks.size() * 1000);

        assertThat(result)
                .isEqualTo(expectedRate);
    }

    @Test
    @DisplayName("로또 결과에 따라 맞는 랭크를 가져오는 지 검증한다.")
    void should_CalculateRank() {
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        List<LottoRank> ranks = lottoResult.calculateRank(lottos);

        AssertionsForInterfaceTypes.assertThat(ranks).containsExactly(LottoRank.SIX_MATCHES);
    }

    private static Stream<Arguments> provideLottoData() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                                new Lotto(List.of(1, 2, 3, 4, 5, 10)),
                                new Lotto(List.of(1, 2, 3, 4, 10, 11)),
                                new Lotto(List.of(1, 2, 3, 10, 11, 12)),
                                new Lotto(List.of(10, 11, 12, 13, 14, 15))
                        ),
                        List.of(
                                LottoRank.SIX_MATCHES,
                                LottoRank.FIVE_MATCHES_BONUS,
                                LottoRank.FIVE_MATCHES,
                                LottoRank.FOUR_MATCHES,
                                LottoRank.THREE_MATCHES,
                                LottoRank.NO_WINNER
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideLottoData")
    @DisplayName("로또 당첨 순위를 올바르게 계산하는 지 검증한다.")
    void should_Return_Correct_Ranks(List<Lotto> lottos, List<LottoRank> expectedLottoRanks) {
        List<LottoRank> ranks = lottoResult.calculateRank(lottos);

        assertThat(ranks).containsExactlyInAnyOrderElementsOf(expectedLottoRanks);
    }

    @Test
    @DisplayName("로또 번호가 3개 미만 일치하면 당첨자가 없다고 반환하는 지 검증한다.")
    void should_Return_No_Winner_If_Under_Three_Matches() {
        List<Lotto> lottos = List.of(
                new Lotto(List.of(10, 11, 12, 13, 14, 15)),
                new Lotto(List.of(1, 10, 11, 12, 13, 14)),
                new Lotto(List.of(1, 2, 10, 11, 12, 13))
        );

        List<LottoRank> ranks = lottoResult.calculateRank(lottos);

        assertThat(ranks).containsOnly(LottoRank.NO_WINNER);
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호에 포함되면 예외를 던지는 지 검증한다.")
    void should_Throw_Exception_If_Bonus_In_Winning() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 3;

        assertThatThrownBy(() -> new LottoResult(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호 목록에 포함될 수 없습니다.");
    }
}
