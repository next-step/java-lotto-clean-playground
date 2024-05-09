package domain.lottoNumber;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {
    @Test
    @DisplayName("로또 숫자 객체 테스트")
    public void LottoNumberClassTest() {
        //given
        List<Integer> expectedLottoNumerList = List.of(1, 2, 3, 4, 5, 6);
        LottoNumber lottoNumber = new LottoNumber(expectedLottoNumerList);

        //when

        //then
        assertThat(expectedLottoNumerList).isEqualTo(lottoNumber.getLottoNumber());

    }
}
