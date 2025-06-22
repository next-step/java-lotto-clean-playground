package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoResultTest {

  private List<Integer> inputNumbers;
  private List<LottoNumber> expectedLottoNumbers;

  @BeforeEach
  void setUp() {
    inputNumbers = new ArrayList<>();
    expectedLottoNumbers = new ArrayList<>();

    for (int i = 1; i <= 6; i++) {
      inputNumbers.add(i);
      expectedLottoNumbers.add(LottoNumber.of(i));
    }
  }

  @Test
  void 로또_결과_객체_생성_테스트() {
    LottoResult lottoResult = LottoResult.of(inputNumbers);

    assertThat(lottoResult.numbers()).containsExactlyElementsOf(expectedLottoNumbers);
  }

  @Test
  void 로또_결과_숫자값_비교_테스트() {
    LottoResult lottoResult = LottoResult.of(inputNumbers);

    List<Integer> actualNumbers = lottoResult.numbers().stream()
        .map(LottoNumber::number)
        .collect(Collectors.toList());

    assertThat(actualNumbers).containsExactlyElementsOf(inputNumbers);
  }

  @ParameterizedTest
  @MethodSource("provideMatchCountTestData")
  void 일치하는_번호_개수_계산_테스트(List<Integer> winningNumbers, List<Integer> userNumbers,
      int expectedMatchCount) {
    LottoResult lottoResult = LottoResult.of(winningNumbers);
    Lotto userLotto = Lotto.of(userNumbers);

    int actualMatchCount = lottoResult.matchCount(userLotto);

    assertEquals(expectedMatchCount, actualMatchCount);
  }

  @Test
  void 동일한_번호_다른_순서로_일치_여부_테스트() {
    List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    List<Integer> userNumbers = Arrays.asList(6, 5, 4, 3, 2, 1);

    LottoResult lottoResult = LottoResult.of(winningNumbers);
    Lotto userLotto = Lotto.of(userNumbers);

    int matchCount = lottoResult.matchCount(userLotto);

    assertThat(matchCount).isEqualTo(6);
  }

  @Test
  void 번호가_하나도_일치하지_않는_경우_테스트() {
    List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    List<Integer> userNumbers = Arrays.asList(7, 8, 9, 10, 11, 12);

    LottoResult lottoResult = LottoResult.of(winningNumbers);
    Lotto userLotto = Lotto.of(userNumbers);

    int matchCount = lottoResult.matchCount(userLotto);

    assertThat(matchCount).isZero();
  }

  private static Stream<Arguments> provideMatchCountTestData() {
    return Stream.of(
        Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(1, 2, 3, 4, 5, 6), 6),
        Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(1, 2, 3, 7, 8, 9), 3),
        Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(7, 8, 9, 10, 11, 12), 0),
        Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(6, 5, 4, 3, 2, 1), 6)
    );
  }
}