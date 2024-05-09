import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Lotto;
import domain.LottoNumber;
import domain.WinningLotto;

public class LottoTest {

	@DisplayName("우승 로또와 비교하여 일치 개수를 결정한다.")
	@Test
	void testMatchedCountWithWinningLotto() {
		WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
		Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 7, 8));
		int actual = 4;
		assertEquals(actual, lotto.getMatchedNumberCount(winningLotto));
	}

	@DisplayName("로또는 보너스 번호를 포함하고 있는지 알 수 있다.")
	@Test
	void testIsContainsBonusNumber() {
		int bonusNumber = 7;
		WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7));
		Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 7, 8));
		boolean actual = true;
		assertEquals(actual, lotto.isContainsBonusNumber(winningLotto.getBonusNumber()));
	}

	@DisplayName("로또는 중복되지 않은 숫자 6개로 이루어져 있다.")
	@Test
	void testIsDuplicate() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Lotto(List.of(1, 2, 3, 4, 5, 5));
		});
	}

	@DisplayName("로또는 오름차순으로 이루어져 있다.")
	@Test
	void testIsNotAscending() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Lotto(List.of(10, 2, 3, 4, 5, 5));
		});
	}
}
