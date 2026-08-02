package domain;

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
  @DisplayName("[테스트 코드] : 당첨번호와 로또번호를 비교했을 때 모두 일치하는 경우")
  void test_당첨번호와_구매한_로또번호가_6개가_매칭된_경우_FIRST_처리() {
    List<LottoMatchResult> lottoMatchResults = List.of(
        createLottoMatchResult(List.of(1, 2, 3, 4, 5, 6))
    );
    LottoRankTable rankTable = new LottoRankTable(lottoMatchResults);
    assertEquals(1L, rankTable.countOf(LottoRank.FIRST));
  }

  @Test
  @DisplayName("[테스트 코드] : 당첨번호와 로또번호를 비교했을 때 5개가 매칭되고 보너스 번호랑 매칭된다면 2등(SECOND)이다.")
  void test_당첨번호와_구매한_로또번호가_5개가_매칭돠고_보너스번호랑_매칭된_경우_SECOND_처리() {
    List<LottoMatchResult> lottoMatchResults = List.of(
        createLottoMatchResult(List.of(1, 2, 3, 4, 5, 7))
    );
    LottoRankTable rankTable = new LottoRankTable(lottoMatchResults);
    assertEquals(1L, rankTable.countOf(LottoRank.SECOND));
  }

  @Test
  @DisplayName("[테스트 코드] : 당첨번호와 로또번호를 비교했을 때 5개가 매칭되고 보너스 번호랑 매칭되지 않으면 3등(THIRD)이다.")
  void test_당첨번호와_구매한_로또번호가_5개가_매칭돠고_보너스번호랑_매칭되지_않은_경우_THIRD_처리() {
    List<LottoMatchResult> lottoMatchResults = List.of(
        createLottoMatchResult(List.of(1, 2, 3, 4, 5, 8))
    );
    LottoRankTable rankTable = new LottoRankTable(lottoMatchResults);
    assertEquals(1L, rankTable.countOf(LottoRank.THIRD));
  }

  @Test
  @DisplayName("[테스트 코드] : 당첨번호와 로또번호를 비교했을 때 4개가 매칭된 경우 4등(FOUR)이다.")
  void test_당첨번호와_구매한_로또번호가_4개가_매칭된_경우_FOUR_처리() {
    List<LottoMatchResult> lottoMatchResults = List.of(
        createLottoMatchResult(List.of(1, 2, 3, 4, 7, 8))
    );
    LottoRankTable rankTable = new LottoRankTable(lottoMatchResults);
    assertEquals(1L, rankTable.countOf(LottoRank.FOUR));
  }

  @Test
  @DisplayName("[테스트 코드] : 당첨번호와 로또번호를 비교했을 때 3개가 매칭된 경우 5등(FIVE)이다.")
  void test_당첨번호와_구매한_로또번호가_3개가_매칭된_경우_FIVE_처리() {
    List<LottoMatchResult> lottoMatchResults = List.of(
        createLottoMatchResult(List.of(1, 2, 3, 7, 8, 9))
    );
    LottoRankTable rankTable = new LottoRankTable(lottoMatchResults);
    assertEquals(1L, rankTable.countOf(LottoRank.FIVE));
  }

  @Test
  @DisplayName("[테스트 코드] : 당첨번호와 로또번호를 비교했을 때 2개가 매칭된 경우 MISS 처리")
  void test_당첨번호와_구매한_로또번호가_2개가_매칭된_경우_MISS_처리() {
    List<LottoMatchResult> lottoMatchResults = List.of(
        createLottoMatchResult(List.of(1, 2, 7, 8, 9, 10))
    );
    LottoRankTable rankTable = new LottoRankTable(lottoMatchResults);
    assertEquals(1L, rankTable.countOf(LottoRank.MISS));
  }

  @Test
  @DisplayName("[테스트 코드] : 당첨번호와 로또번호를 비교했을 때 1개가 매칭된 경우 MISS 처리")
  void test_당첨번호와_구매한_로또번호가_1개가_매칭된_경우_MISS_처리() {
    List<LottoMatchResult> lottoMatchResults = List.of(
        createLottoMatchResult(List.of(1, 7, 8, 9, 10, 11))
    );
    LottoRankTable rankTable = new LottoRankTable(lottoMatchResults);
    assertEquals(1L, rankTable.countOf(LottoRank.MISS));
  }

  @Test
  @DisplayName("[테스트 코드] : 당첨번호와 로또번호를 비교했을 때 0개가 매칭된 경우 MISS 처리")
  void test_당첨번호와_구매한_로또번호가_0개가_매칭된_경우_MISS_처리() {
    List<LottoMatchResult> lottoMatchResults = List.of(
        createLottoMatchResult(List.of(7, 8, 9, 10, 11, 12))
    );
    LottoRankTable rankTable = new LottoRankTable(lottoMatchResults);
    assertEquals(1L, rankTable.countOf(LottoRank.MISS));
  }

  @Test
  @DisplayName("[테스트 코드] : 당첨번호와 구매한 로또 번호를 비교해서 랭크에 맞는 로또가 몇장인지 계산 테스트.")
  void test_당첨번호와_구매한_로또번호가_매칭된_횟수_계산_테스트() {
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
  void test_당첨번호와_구매한_로또번호를_비교해서_총_상금을_계산_테스트() {
    List<LottoMatchResult> lottoMatchResults = List.of(
        createLottoMatchResult(List.of(1, 2, 3, 4, 8, 9)), // 4개 매칭 / 50000
        createLottoMatchResult(List.of(1, 2, 3, 8, 9, 10)) // 3개 매칭 / 5000
    );
    LottoRankTable rankTable = new LottoRankTable(lottoMatchResults);

    assertEquals(55000L, rankTable.sumPrize());
  }
}
