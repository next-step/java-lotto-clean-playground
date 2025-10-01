package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomLottoNumberGeneratorTest {

    @Test
    void 랜덤생성기는_항상_6개의_번호를_만든다() {
        RandomLottoNumberGenerator generator = new RandomLottoNumberGenerator();
        LottoNumbers lottoNumbers = generator.generate();
        assertEquals(6, lottoNumbers.getNumbers().size());
    }
}
