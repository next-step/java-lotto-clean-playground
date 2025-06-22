package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoRankTest {

  @ParameterizedTest
  @CsvSource({
      "0, NONE",
      "1, NONE",
      "2, NONE",
      "3, FIFTH",
      "4, FOURTH",
      "5, THIRD",
      "6, FIRST"
  })
  void 일치하는_번호_개수에_따른_로또_등수_반환_테스트(int matchCount, Rank expectedRank) {
    Rank actualRank = Rank.valueOf(matchCount);

    assertThat(actualRank).isEqualTo(expectedRank);
  }

  @ParameterizedTest
  @ValueSource(ints = {7, 8, 10, Integer.MAX_VALUE})
  void 유효하지_않은_일치_개수는_NONE을_반환해야_함(int invalidMatchCount) {
    Rank actualRank = Rank.valueOf(invalidMatchCount);

    assertThat(actualRank).isEqualTo(Rank.NONE);
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, -2, -3, -100, Integer.MIN_VALUE})
  void 일치_개수가_음수라면_예외처리_되어야한다(int invalidMatchCount) {
    assertThatThrownBy(() -> Rank.valueOf(invalidMatchCount)).isInstanceOf(RuntimeException.class);
  }

  @Test
  void 각_등수별_일치_개수_반환_테스트() {
    // NONE은 0, 1, 2 중 하나의 매치 카운트를 가짐 (일반적으로 0을 사용)
    assertEquals(0, Rank.NONE.getMatchCount());
    assertEquals(3, Rank.FIFTH.getMatchCount());
    assertEquals(4, Rank.FOURTH.getMatchCount());
    assertEquals(5, Rank.THIRD.getMatchCount());
    assertEquals(5, Rank.SECOND.getMatchCount()); // 2등도 5개 일치 (보너스 볼 포함)
    assertEquals(6, Rank.FIRST.getMatchCount());
  }

  @Test
  void 상수_순서_검증_테스트() {
    assertThat(Rank.NONE.ordinal()).isEqualTo(0);
    assertThat(Rank.FIFTH.ordinal()).isEqualTo(1);
    assertThat(Rank.FOURTH.ordinal()).isEqualTo(2);
    assertThat(Rank.THIRD.ordinal()).isEqualTo(3);
    assertThat(Rank.SECOND.ordinal()).isEqualTo(4);
    assertThat(Rank.FIRST.ordinal()).isEqualTo(5);
  }

  @Test
  void 상수_이름_검증_테스트() {
    assertThat(Rank.NONE.name()).isEqualTo("NONE");
    assertThat(Rank.FIFTH.name()).isEqualTo("FIFTH");
    assertThat(Rank.FOURTH.name()).isEqualTo("FOURTH");
    assertThat(Rank.THIRD.name()).isEqualTo("THIRD");
    assertThat(Rank.SECOND.name()).isEqualTo("SECOND");
    assertThat(Rank.FIRST.name()).isEqualTo("FIRST");
  }

  @Test
  void 상수_값_검증_테스트() {
    assertThat(Rank.NONE.ordinal()).isEqualTo(0);
    assertThat(Rank.FIFTH.ordinal()).isEqualTo(1);
    assertThat(Rank.FOURTH.ordinal()).isEqualTo(2);
    assertThat(Rank.THIRD.ordinal()).isEqualTo(3);
    assertThat(Rank.SECOND.ordinal()).isEqualTo(4);
    assertThat(Rank.FIRST.ordinal()).isEqualTo(5);

    assertThat(Rank.NONE.name()).isEqualTo("NONE");
    assertThat(Rank.FIFTH.name()).isEqualTo("FIFTH");
    assertThat(Rank.FOURTH.name()).isEqualTo("FOURTH");
    assertThat(Rank.THIRD.name()).isEqualTo("THIRD");
    assertThat(Rank.SECOND.name()).isEqualTo("SECOND");
    assertThat(Rank.FIRST.name()).isEqualTo("FIRST");
  }
}