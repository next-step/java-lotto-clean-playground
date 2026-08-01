package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRankTest {
  @Test
  @DisplayName("[테스트코드] : 매칭 개수가 3개면 5등으로 판별하는지 테스트")
  void test_매칭개수가_3개인_경우_5등으로_판별하는지_테스트() {
    assertEquals(LottoRank.FIVE, LottoRank.findByMatchingResult(3L, false));
  }

  @Test
  @DisplayName("[테스트코드] : 매칭 개수가 4개면 4등으로 판별하는지 테스트")
  void test_매칭개수가_4개인_경우_4등으로_판별하는지_테스트() {
    assertEquals(LottoRank.FOUR, LottoRank.findByMatchingResult(4L, false));
  }

  @Test
  @DisplayName("[테스트코드] : 매칭 개수가 5개고 보너스 번호가 일치하지 않으면 3등으로 판별하는지 테스트")
  void test_매칭개수가_5개고_보너스_불일치인_경우_3등으로_판별하는지_테스트() {
    assertEquals(LottoRank.THIRD, LottoRank.findByMatchingResult(5L, false));
  }

  @Test
  @DisplayName("[테스트코드] : 매칭 개수가 5개고 보너스 번호가 일치하면 2등으로 판별하는지 테스트")
  void test_매칭개수가_5개고_보너스_일치인_경우_2등으로_판별하는지_테스트() {
    assertEquals(LottoRank.SECOND, LottoRank.findByMatchingResult(5L, true));
  }

  @Test
  @DisplayName("[테스트코드] : 매칭 개수가 6개면 1등으로 판별하는지 테스트")
  void test_매칭개수가_6개인_경우_1등으로_판별하는지_테스트() {
    assertEquals(LottoRank.FIRST, LottoRank.findByMatchingResult(6L, false));
  }

  @Test
  @DisplayName("[테스트코드] : 매칭 개수가 0개면 MISS로 판별하는지 테스트")
  void test_매칭개수가_0개인_경우_MISS로_판별하는지_테스트() {
    assertEquals(LottoRank.MISS, LottoRank.findByMatchingResult(0L, false));
  }

  @Test
  @DisplayName("[테스트코드] : 매칭 개수가 4개면 보너스 번호가 일치해도 4등으로 판별하는지 테스트")
  void test_매칭개수가_4개면_보너스_일치여부와_무관하게_4등으로_판별하는지_테스트() {
    assertEquals(LottoRank.FOUR, LottoRank.findByMatchingResult(4L, true));
  }

  @Test
  @DisplayName("[테스트코드] : 매칭 개수가 3개면 보너스 번호가 일치해도 5등으로 판별하는지 테스트")
  void test_매칭개수가_3개면_보너스_일치여부와_무관하게_5등으로_판별하는지_테스트() {
    assertEquals(LottoRank.FIVE, LottoRank.findByMatchingResult(3L, true));
  }
}
