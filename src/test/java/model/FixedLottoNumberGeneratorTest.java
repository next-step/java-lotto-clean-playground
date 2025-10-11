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
        LottoTicket lotto = generator.generate();

        // Then
        assertEquals(input.toString(), lotto.getNumbers().toString());
    }

    @Test
    void 숫자가_6개가_아니면_예외가_발생한다() {
        // Given
        List<Integer> input = List.of(1, 2, 3, 4, 5);

        // When & Then
        assertThrows(IllegalArgumentException.class,
                () -> new FixedLottoNumberGenerator(input));
    }

    @Test
    void 숫자가_1에서_45범위를_벗어나면_예외가_발생한다() {
        // Given
        List<Integer> input = List.of(1, 2, 3, 4, 5, 46);

        // When & Then
        assertThrows(IllegalArgumentException.class,
                () -> new FixedLottoNumberGenerator(input));
    }

}
