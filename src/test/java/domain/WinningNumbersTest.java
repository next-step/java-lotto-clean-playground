package domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {

  private final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

  @Test
  @DisplayName("[테스트 코드] : 당첨번호와 구매한 로또의 일치 개수를 정확히 계산한다")
  void test_당첨번호와_일치하는_개수_계산(){
    assertAll(
        () -> assertEquals(6L, winningNumbers.countMatch(new Lotto(List.of(1, 2, 3, 4, 5, 6)))),
        () -> assertEquals(3L, winningNumbers.countMatch(new Lotto(List.of(1, 2, 3, 7, 8, 9)))),
        () -> assertEquals(0L, winningNumbers.countMatch(new Lotto(List.of(7, 8, 9, 10, 11, 12))))
    );
  }
}
