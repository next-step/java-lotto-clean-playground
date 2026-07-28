import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRankTest {
  @Test
  @DisplayName(" [테스트코드] : 지난 주 1등번호와 비교한 결과 매칭된 숫자의 갯수로 등수를 설정")
  void test_매칭된_갯수에_맞는_등수반환_테스트(){
    assertAll(
        ()-> assertEquals(LottoRank.FOUR,LottoRank.findByMatchingCount(3L)),
        ()-> assertEquals(LottoRank.THIRD,LottoRank.findByMatchingCount(4L)),
        ()-> assertEquals(LottoRank.SECOND,LottoRank.findByMatchingCount(5L)),
        ()-> assertEquals(LottoRank.FIRST,LottoRank.findByMatchingCount(6L)),
        ()-> assertEquals(LottoRank.MISS,LottoRank.findByMatchingCount(0L))
    );
  }



}
