package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FixedLottoNumberGeneratorTest {

    @Test
    void 고정된숫자를_반환() {
        // Given
        List<Integer> input = List.of(1, 2, 3, 4, 5, 6);
        FixedLottoNumberGenerator generator = new FixedLottoNumberGenerator(input);

        // When
        LottoNumbers lotto = generator.generate();

        // Then
        // LottoNumbers.getNumbers()는 LottoNumber 객체들이라서
        // 그대로 toString()해서 비교하면 간단해짐
        assertEquals(input.toString(), lotto.getNumbers().toString());
    }
}
