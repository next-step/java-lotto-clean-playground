import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import domain.Lotto;
import domain.LottoSystem;
import generator.RandomLottoNumberGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoSystemTest {
  @Test
  @DisplayName("[테스트코드] 입력된 값이 LOTTO_PRICE(=1000)보다 적은 경우 예외발생")
  void test_입력된_값이_LOTTO_PRICE_보다_적은_경우_예외발생(){
   assertThrows(IllegalArgumentException.class, ()->new LottoSystem(500, new RandomLottoNumberGenerator()));
  }
}
