package domain;

import constant.LottoConstant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumbersGeneratorTest {

    @Test
    @DisplayName("OK : 생성된 숫자 리스트의 사이즈가 로또 사이즈와 동일하다.")
    void generateNumbersSizeEqualToLottoNumbersSize() {
        LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGenerator();
        assertThat(lottoNumbersGenerator.getNumbers().size()).isEqualTo(LottoConstant.LOTTO_NUMBERS_SIZE);
    }
}