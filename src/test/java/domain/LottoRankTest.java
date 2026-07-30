package domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRankTest {
  @Test
  @DisplayName("[테스트코드] : 지난 주 1등번호와 비교한 결과 매칭된 숫자의 갯수로 등수를 설정")
  void test_매칭된_갯수에_맞는_등수반환_테스트() {
    assertAll(
        () -> assertEquals(LottoRank.FIVE, LottoRank.findByMatchingResult(3L, false)),
        () -> assertEquals(LottoRank.FOUR, LottoRank.findByMatchingResult(4L, false)),
        () -> assertEquals(LottoRank.THIRD, LottoRank.findByMatchingResult(5L, false)),
        () -> assertEquals(LottoRank.SECOND, LottoRank.findByMatchingResult(5L, true)),
        () -> assertEquals(LottoRank.FIRST, LottoRank.findByMatchingResult(6L, false)),
        () -> assertEquals(LottoRank.MISS, LottoRank.findByMatchingResult(0L, false))
    );
  }

  @Test
  @DisplayName("[테스트코드] : 5개 일치가 아니면 보너스 번호 일치 여부와 무관하게 등수가 결정된다")
  void test_5개_일치가_아니면_보너스_일치여부와_무관하게_등수가_결정된다() {
    assertAll(
        () -> assertEquals(LottoRank.FOUR, LottoRank.findByMatchingResult(4L, true)),
        () -> assertEquals(LottoRank.FIVE, LottoRank.findByMatchingResult(3L, true))
    );
  }
}
