package lotto.domain;

import java.util.EnumMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WinningResultTest {

  private Map<Rank, Integer> rankStats;

  @BeforeEach
  void setUp() {
    rankStats = new EnumMap<>(Rank.class);

    for (Rank rank : Rank.values()) {
      rankStats.put(rank, 0);
    }
  }

  @Test
  void 빈_당첨_결과_생성시_모든_등수가_0으로_초기화된다() {
    WinningResult winningResult = new WinningResult(rankStats);
    Map<Rank, Integer> result = winningResult.getWinningResult();

    assertThat(result.get(Rank.NONE)).isEqualTo(0);
    assertThat(result.get(Rank.FOURTH)).isEqualTo(0);
    assertThat(result.get(Rank.THIRD)).isEqualTo(0);
    assertThat(result.get(Rank.SECOND)).isEqualTo(0);
    assertThat(result.get(Rank.FIRST)).isEqualTo(0);
  }

  @Test
  void 일치_개수_추가시_해당_등수의_카운트가_증가한다() {
    rankStats.put(Rank.FIFTH, 2);
    rankStats.put(Rank.FIRST, 1);
    WinningResult winningResult = new WinningResult(rankStats);

    Map<Rank, Integer> result = winningResult.getWinningResult();

    assertThat(result.get(Rank.FIFTH)).isEqualTo(2);
    assertThat(result.get(Rank.FIRST)).isEqualTo(1);
  }

  @ParameterizedTest
  @MethodSource("provideMatchCountsAndTotalPrize")
  void 총_상금_계산_테스트(int[] matchCounts, long expectedTotalPrize) {
    for (int matchCount : matchCounts) {
      final Rank rank = Rank.valueOf(matchCount);
      rankStats.put(rank, rankStats.getOrDefault(rank, 0) + 1);
    }

    WinningResult winningResult = new WinningResult(rankStats);

    long actualTotalPrize = winningResult.calculateTotalPrize();

    assertThat(actualTotalPrize).isEqualTo(expectedTotalPrize);
  }

  private static Stream<Arguments> provideMatchCountsAndTotalPrize() {
    return Stream.of(
        Arguments.of(new int[]{0, 1, 2}, 0L),

        Arguments.of(new int[]{3}, 5_000L),

        Arguments.of(new int[]{4}, 50_000L),

        Arguments.of(new int[]{5}, 1_500_000L),

        Arguments.of(new int[]{6}, 2_000_000_000L),

        Arguments.of(new int[]{3, 3, 4, 5}, 1_560_000L),

        Arguments.of(new int[]{0, 1, 2, 3, 4, 5, 6}, 2_001_555_000L)
    );
  }

  @Test
  void 특정_등수_카운트_조회_테스트() {
    rankStats.put(Rank.FIFTH, 2);
    rankStats.put(Rank.FOURTH, 2);
    rankStats.put(Rank.FIRST, 1);
    WinningResult winningResult = new WinningResult(rankStats);

    Map<Rank, Integer> result = winningResult.getWinningResult();

    assertThat(result.get(Rank.FIFTH)).isEqualTo(2);
    assertThat(result.get(Rank.FOURTH)).isEqualTo(2);
    assertThat(result.get(Rank.THIRD)).isEqualTo(0);
    assertThat(result.get(Rank.SECOND)).isEqualTo(0);
    assertThat(result.get(Rank.FIRST)).isEqualTo(1);
    assertThat(result.get(Rank.NONE)).isEqualTo(0);
    assertThat(result.get(Rank.NONE)).isEqualTo(0);
    assertThat(result.get(Rank.NONE)).isEqualTo(0);
  }

  @Test
  void 모든_등급_통계를_포함하는지_테스트() {
    WinningResult winningResult = new WinningResult(rankStats);

    Map<Rank, Integer> result = winningResult.getWinningResult();

    assertThat(result).containsKeys(
        Rank.NONE, Rank.NONE, Rank.NONE,
        Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST
    );
  }
}