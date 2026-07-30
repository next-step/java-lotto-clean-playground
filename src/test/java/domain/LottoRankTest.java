package domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRankTest {
  @Test
  @DisplayName("[테스트코드] : 지난 주 1등번호와 비교한 결과 매칭된 숫자의 갯수로 등수를 설정")
  void test_매칭된_갯수에_맞는_등수반환_테스트(){
    assertAll(
        ()-> assertEquals(LottoRank.FIVE,LottoRank.findByMatchingResult(3L, false)),
        ()-> assertEquals(LottoRank.FOUR,LottoRank.findByMatchingResult(4L, false)),
        ()-> assertEquals(LottoRank.THIRD,LottoRank.findByMatchingResult(5L, false)),
        ()-> assertEquals(LottoRank.SECOND,LottoRank.findByMatchingResult(5L, true)),
        ()-> assertEquals(LottoRank.FIRST,LottoRank.findByMatchingResult(6L, false)),
        ()-> assertEquals(LottoRank.MISS,LottoRank.findByMatchingResult(0L, false))
    );
  }
}
