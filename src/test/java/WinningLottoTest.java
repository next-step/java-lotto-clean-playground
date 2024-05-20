import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Lotto;
import domain.LottoNumber;
import domain.WinningLotto;

public class WinningLottoTest {

	@DisplayName("우승 로또 번호가 보너스 번호를 포함하면 안된다.")
	@Test
	void testWinningLottoContainsBonusNumber() {
		assertThrows(IllegalArgumentException.class,
			() -> new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(6)));
	}
}
