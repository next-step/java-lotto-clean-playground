package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoResultCheckerTest {

  private final LottoResult lottoResult = LottoResult.of(List.of(1, 2, 3, 4, 5, 6));
  private final LottoNumber bonusBall = LottoNumber.of(7);
  private final LottoResultChecker lottoResultChecker = new LottoResultChecker(lottoResult,
      bonusBall);

  @ParameterizedTest
  @MethodSource("provideLottosAndWinningResult")
  void 여러_로또의_당첨_통계를_확인한다(Lottos userLottos, Map<Rank, Integer> expectedResult) {
    WinningResult winningResult = lottoResultChecker.matchLottos(userLottos);

    assertThat(winningResult.getWinningResult()).isEqualTo(expectedResult);
  }

  private static Stream<Arguments> provideLottosAndWinningResult() {
    Map<Rank, Integer> case1 = new HashMap<>();
    case1.put(Rank.FIRST, 1);
    case1.put(Rank.SECOND, 0);
    case1.put(Rank.THIRD, 0);
    case1.put(Rank.FOURTH, 0);
    case1.put(Rank.FIFTH, 0);
    case1.put(Rank.NONE, 0);

    Map<Rank, Integer> case2 = new HashMap<>();
    case2.put(Rank.FIRST, 1);
    case2.put(Rank.SECOND, 1);
    case2.put(Rank.THIRD, 0);
    case2.put(Rank.FOURTH, 1);
    case2.put(Rank.FIFTH, 1);
    case2.put(Rank.NONE, 0);

    Map<Rank, Integer> case3 = new HashMap<>();
    case3.put(Rank.FIRST, 0);
    case3.put(Rank.SECOND, 0);
    case3.put(Rank.THIRD, 0);
    case3.put(Rank.FOURTH, 0);
    case3.put(Rank.FIFTH, 0);
    case3.put(Rank.NONE, 2);

    return Stream.of(
        Arguments.of(
            new Lottos(List.of(Lotto.of(List.of(1, 2, 3, 4, 5, 6)))),
            case1
        ),
        Arguments.of(
            new Lottos(List.of(
                Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.of(List.of(1, 2, 3, 4, 5, 7)),
                Lotto.of(List.of(1, 2, 3, 4, 7, 8)),
                Lotto.of(List.of(1, 2, 3, 7, 8, 9))
            )),
            case2
        ),
        Arguments.of(
            new Lottos(List.of(
                Lotto.of(List.of(7, 8, 9, 10, 11, 12)),
                Lotto.of(List.of(13, 14, 15, 16, 17, 18))
            )),
            case3
        )
    );
  }

  @Test
  void 당첨_금액_계산_테스트() {
    Lottos userLottos = new Lottos(Arrays.asList(
        Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
        Lotto.of(List.of(1, 2, 3, 4, 5, 7)),
        Lotto.of(List.of(1, 2, 3, 4, 7, 8)),
        Lotto.of(List.of(1, 2, 3, 7, 8, 9))
    ));

    WinningResult winningResult = lottoResultChecker.matchLottos(userLottos);
    long totalPrizeMoney = winningResult.calculateTotalPrize();

    long expectedPrizeMoney = 2_000_000_000L + 30_000_000L + 50_000L + 5_000L;
    assertThat(totalPrizeMoney).isEqualTo(expectedPrizeMoney);
  }

  @Test
  void 당첨_통계_출력_테스트() {
    Lottos userLottos = new Lottos(Arrays.asList(
        Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
        Lotto.of(List.of(1, 2, 3, 4, 5, 7)),
        Lotto.of(List.of(1, 2, 3, 4, 5, 8)),
        Lotto.of(List.of(1, 2, 3, 4, 8, 9)),
        Lotto.of(List.of(7, 8, 9, 10, 11, 12))
    ));

    WinningResult winningResult = lottoResultChecker.matchLottos(userLottos);
    Map<Rank, Integer> result = winningResult.getWinningResult();

    assertThat(result.get(Rank.FIRST)).isEqualTo(1);
    assertThat(result.get(Rank.SECOND)).isEqualTo(1);
    assertThat(result.get(Rank.THIRD)).isEqualTo(1);
    assertThat(result.get(Rank.FOURTH)).isEqualTo(1);
    assertThat(result.get(Rank.NONE)).isEqualTo(1);
  }

}