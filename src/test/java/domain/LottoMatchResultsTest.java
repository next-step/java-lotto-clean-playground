package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMatchResultsTest {

  private final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 45);

  @Test
  @DisplayName("[테스트 코드] : 총 상금과 구매 금액으로 수익률을 계산한다")
  void test_수익률_계산(){
    List<LottoMatchResult> results = List.of(
        new LottoMatchResult(new Lotto(List.of(1, 2, 3, 4, 8, 9)), winningNumbers),  // 4등 / 50000원
        new LottoMatchResult(new Lotto(List.of(1, 2, 3, 8, 9, 10)), winningNumbers)  // 5등 / 5000원
    );

    LottoMatchResults lottoMatchResults = new LottoMatchResults(results, 2000);

    assertEquals(27.5, lottoMatchResults.profitRate());
  }

  @Test
  @DisplayName("[테스트 코드] : 당첨된 로또가 없으면 수익률은 0이다")
  void test_당첨_없을_때_수익률_0(){
    List<LottoMatchResult> results = List.of(
        new LottoMatchResult(new Lotto(List.of(7, 8, 9, 10, 11, 12)), winningNumbers)
    );

    LottoMatchResults lottoMatchResults = new LottoMatchResults(results, 1000);

    assertEquals(0.0, lottoMatchResults.profitRate());
  }
}
