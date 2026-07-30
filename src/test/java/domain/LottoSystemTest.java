package domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoSystemTest {
  @Test
  @DisplayName("[테스트코드] 입력된 값이 LOTTO_PRICE(=1000)보다 적은 경우 예외발생")
  void test_입력된_값이_LOTTO_PRICE_보다_적은_경우_예외발생() {
   assertThrows(IllegalArgumentException.class, () -> new LottoSystem(500, new RandomLottoNumberGenerator()));
  }

  @Test
  @DisplayName("[테스트코드] 번호 생성기가 null인 경우 예외발생")
  void test_번호_생성기가_null인_경우_예외발생() {
    assertThrows(IllegalArgumentException.class, () -> new LottoSystem(1000, null));
  }
}
