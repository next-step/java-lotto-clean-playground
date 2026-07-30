package domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRankTableTest {

  private final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

  private LottoMatchResult createLottoMatchResult(List<Integer> lottoNumbers) {
    return new LottoMatchResult(new Lotto(lottoNumbers), winningNumbers);
  }

  @Test
  @DisplayName("[테스트 코드] : 당첨번호와 로또번호를 비교해서 각각 매칭되는지 테스트.")
  void test_당첨번호와_구매한_로또번호가_알맞게_매칭되는지_테스트(){
    List<LottoMatchResult> lottoMatchResults = List.of(
        createLottoMatchResult(List.of(1, 2, 3, 4, 5, 6)),
        createLottoMatchResult(List.of(1, 2, 3, 4, 5, 7)),
        createLottoMatchResult(List.of(1, 2, 3, 4, 8, 9)),
        createLottoMatchResult(List.of(1, 2, 3, 8, 9, 10)),
        createLottoMatchResult(List.of(8, 9, 10, 11, 12, 13))
    );

    LottoRankTable rankTable = new LottoRankTable(lottoMatchResults);

    assertAll(
        () -> assertEquals(1L, rankTable.countOf(LottoRank.FIRST)),
        () -> assertEquals(1L, rankTable.countOf(LottoRank.SECOND)),
        () -> assertEquals(0L, rankTable.countOf(LottoRank.THIRD)),
        () -> assertEquals(1L, rankTable.countOf(LottoRank.FOUR)),
        () -> assertEquals(1L, rankTable.countOf(LottoRank.FIVE)),
        () -> assertEquals(1L, rankTable.countOf(LottoRank.MISS))
    );
  }

  @Test
  @DisplayName("[테스트 코드] : 당첨번호와 구매한 로또 번호를 비교해서 랭크에 맞는 로또가 몇장인지 계산 테스트.")
  void test_당첨번호와_구매한_로또번호가_매칭된_횟수_계산_테스트(){
    List<LottoMatchResult> lottoMatchResults = List.of(
        createLottoMatchResult(List.of(1, 2, 3, 40, 41, 42)),
        createLottoMatchResult(List.of(1, 2, 3, 7, 8, 9)),
        createLottoMatchResult(List.of(1, 2, 3, 9, 10, 11))
    );
    LottoRankTable rankTable = new LottoRankTable(lottoMatchResults);

    assertEquals(3L, rankTable.countOf(LottoRank.FIVE));
  }

  @Test
  @DisplayName("[테스트 코드] : 당첨번호와 구매한 로또 번호를 비교해서 랭크에 맞는 금액이 적절하게 더해서 반환되는지 테스트")
  void test_당첨번호와_구매한_로또번호를_비교해서_총_상금을_계산_테스트(){

    List<LottoMatchResult> lottoMatchResults = List.of(
        createLottoMatchResult(List.of(1, 2, 3, 4, 8, 9)), // 4개 매칭 / 50000
        createLottoMatchResult(List.of(1, 2, 3, 8, 9, 10)) // 3개 매칭 / 5000
    );
    LottoRankTable rankTable = new LottoRankTable(lottoMatchResults);

    assertEquals(55000L, rankTable.sumPrize());
  }
}
