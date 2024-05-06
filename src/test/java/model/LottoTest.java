package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
    @Test
    void convertToList_ValidInput() {
        String lottoNumbersStr = "1, 2, 3, 4, 5, 6";
        Lotto lotto = new Lotto().convertToList(lottoNumbersStr);

        List<Integer> expectedNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertEquals(expectedNumbers, lotto.getNumbers());
    }

    @Test
    void convertToList_InvalidInput() {
        String lottoNumbersStr = "1, 2, 3, 4, A, 6";
        assertThrows(NumberFormatException.class, () -> new Lotto().convertToList(lottoNumbersStr));
    }

    @Test
    void validateSize_ValidSize() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        assertDoesNotThrow(() -> lotto.validateSize(numbers));
    }

    @Test
    void validateSize_InvalidSize() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Lotto lotto = new Lotto(numbers);
        assertThrows(IllegalArgumentException.class, () -> lotto.validateSize(numbers));
    }

    @Test
    void validateRange_ValidRange() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        assertDoesNotThrow(() -> lotto.validateRange(numbers));
    }

    @Test
    void validateRange_InvalidRange() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 50, 6);
        Lotto lotto = new Lotto(numbers);
        assertThrows(IllegalArgumentException.class, () -> lotto.validateRange(numbers));
    }

    @Test
    void validateDuplicate_NoDuplicates() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        assertDoesNotThrow(() -> lotto.validateDuplicate(numbers));
    }

    @Test
    void validateDuplicate_DuplicateNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 4, 6);
        Lotto lotto = new Lotto(numbers);
        assertThrows(IllegalArgumentException.class, () -> lotto.validateDuplicate(numbers));
    }

    @Test
    void calculateMatches_WithWinningLotto() {
        List<Integer> myNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto myLotto = new Lotto(myNumbers);

        List<Integer> winningNumbers = Arrays.asList(1, 3, 5, 7, 9, 11);
        Lotto winningLotto = new Lotto(winningNumbers);

        assertEquals(3, myLotto.calculateMatches(winningLotto));
    }

    @Test
    void validateDuplicateWithBonus_ValidBonusNumber() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        assertDoesNotThrow(() -> lotto.validateDuplicateWithBonus(7));
    }

    @Test
    void validateDuplicateWithBonus_InvalidBonusNumber() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        assertThrows(IllegalArgumentException.class, () -> lotto.validateDuplicateWithBonus(6));
    }
}