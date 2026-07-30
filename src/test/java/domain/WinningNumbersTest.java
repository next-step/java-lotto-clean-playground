package domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {

  private final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 45);

  @Test
  @DisplayName("[테스트 코드] : 당첨번호와 구매한 로또의 일치 개수를 정확히 계산한다")
  void test_당첨번호와_일치하는_개수_계산(){
    assertAll(
        () -> assertEquals(6L, winningNumbers.countMatch(new Lotto(List.of(1, 2, 3, 4, 5, 6)))),
        () -> assertEquals(3L, winningNumbers.countMatch(new Lotto(List.of(1, 2, 3, 7, 8, 9)))),
        () -> assertEquals(0L, winningNumbers.countMatch(new Lotto(List.of(7, 8, 9, 10, 11, 12))))
    );
  }

  @Test
  @DisplayName("[테스트 코드] : 보너스 번호의 범위는 반드시 MIN_NUMBER(=1)부터 MAX_NUMBER(=45) 사이 값이어야 한다.")
  void test_보너스번호_범위가_MIN_NUMBER부터_MAX_NUMBER_사이_값이_아닌_경우_예외발생(){
    assertAll(
        () -> assertThrows(IllegalArgumentException.class, () -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 1000)),
        () -> assertThrows(IllegalArgumentException.class, () -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), -1000))
    );
  }

  @Test
  @DisplayName("[테스트 코드] : 당첨번호에 존재하는 보너스 번호는 허용하지 않는다")
  void test_당첨번호와_중복된_보너스번호_예외발생(){
    assertThrows(IllegalArgumentException.class, () -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 6));
  }

  @Test
  @DisplayName("[테스트 코드] : 정상적으로 2등이 결정되는지 확인")
  void test_정상적으로_보너스번호_입력_후_2등_판별(){
    Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
    WinningNumbers winningNumbersWithMatchingBonus = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
    LottoMatchResult result = new LottoMatchResult(lotto, winningNumbersWithMatchingBonus);

    assertEquals(LottoRank.SECOND, result.getRank());
  }
}
