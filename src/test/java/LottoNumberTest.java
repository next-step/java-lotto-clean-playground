import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class LottoNumberTest {

    @Test
    @DisplayName("유효한 번호로 LottoNumber를 생성")
    void lottoNumberWithValidNumber() {
        LottoNumber.from(1);
        LottoNumber.from(45);
    }

    @Test
    @DisplayName("1~45 범위를 벗어나면 예외")
    void throwExceptionNumberIsOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.from(0));

        assertThrows(IllegalArgumentException.class, () -> LottoNumber.from(46));
    }
}
