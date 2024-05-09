import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.LottoNumber;

public class LottoNumberTest {

	@DisplayName("로또 번호는 1부터 45사이여야 한다.")
	@Test
	void lottoNumberRangeTest() {
		int lottoNumber1 = 0;
		int lottoNumber2 = 46;
		assertThrows(IllegalArgumentException.class, () -> new LottoNumber(lottoNumber1));
		assertThrows(IllegalArgumentException.class, () -> new LottoNumber(lottoNumber2));
	}
}
