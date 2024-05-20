import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Lottos;

public class LottosTest {

	@DisplayName("수동 로또의 개수가 구입 로또 개수보다 많을 수 없다.")
	@Test
	void testMaunalLottoCount() {
		int manualLottoCount = 25;
		int totalLottoCount = 15000 / 1000;
		List<List<Integer>> nullish = new ArrayList<>();
		assertThrows(IllegalArgumentException.class, () -> new Lottos(manualLottoCount, totalLottoCount, nullish));
	}
}
