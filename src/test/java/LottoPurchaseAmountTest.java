import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.LottoPurchaseAmount;

public class LottoPurchaseAmountTest {

	@DisplayName("로또 구입 금액은 1000원 이상이여야 한다.")
	@Test
	void testLottoPurchaseAmount() {
		assertThrows(IllegalArgumentException.class, () -> new LottoPurchaseAmount(500));
	}
	
	@DisplayName("로도 금액은 1000원 단위여야 한다.")
	@Test
	void testLottoPurchaseAmountUnit() {
		assertThrows(IllegalArgumentException.class, () -> new LottoPurchaseAmount(1800));
	}
}
