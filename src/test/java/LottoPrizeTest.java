import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoPrize;
import domain.WinningLotto;

public class LottoPrizeTest {

	@DisplayName("등수는 번호 매치 개수와 보너스 번호로 결정된다.")
	@Test
	void testRankingBasedOnMatchCountAndBonus() {
		assertEquals(LottoPrize.LAST_PRIZE, LottoPrize.of(0, false));
		assertEquals(LottoPrize.LAST_PRIZE, LottoPrize.of(1, false));
		assertEquals(LottoPrize.LAST_PRIZE, LottoPrize.of(2, false));
		assertEquals(LottoPrize.FIFTH_PRIZE, LottoPrize.of(3, false));
		assertEquals(LottoPrize.FOURTH_PRIZE, LottoPrize.of(4, false));
		assertEquals(LottoPrize.THIRD_PRIZE, LottoPrize.of(5, false));
		assertEquals(LottoPrize.SECOND_PRIZE, LottoPrize.of(5, true));
		assertEquals(LottoPrize.FIRST_PRIZE, LottoPrize.of(6, false));
	}

	@DisplayName("당첨개수가 5개가 아니라면, 보너스와 관련없이 번호 매치 개수로 등수를 결정한다.")
	@Test
	void testThirdPrize() {
		WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
		LottoNumber bonusNumber = new LottoNumber(7);
		Lotto lotto = new Lotto(List.of(7, 8, 9, 10, 11, 12));
		LottoPrize expected = LottoPrize.of(lotto.getMatchedNumberCount(winningLotto),
			lotto.isContainsBonusNumber(bonusNumber));
		LottoPrize actual = LottoPrize.LAST_PRIZE;
		assertEquals(actual, expected);
	}
}
