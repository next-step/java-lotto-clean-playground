package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Errors;

public record Lotto(List<Integer> numbers) {

    public final static int SIZE = 6;
    public final static int MIN_NUMBER = 1;
    public final static int MAX_NUMBER = 45;
    public final static int PRICE = 1000;

    public Lotto {
        validateNumbers(numbers);
    }

    private void validateNumbers(List<Integer> numbers) {
        validateNumbersRange(numbers);
        validateLottoNumbersSize(numbers);
        validateDuplicateNumbers(numbers);
    }

    private void validateNumbersRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            validateNumberRange(number);
        }
    }

    private void validateNumberRange(Integer number) {
        if (!isNumberInRange(number)) {
            throw new IllegalArgumentException(Errors.NUMBER_IS_NOT_IN_VALID_RANGE);
        }
    }

    private void validateDuplicateNumbers(List<Integer> numbers) {
        if (hasDuplicates(numbers)) {
            throw new IllegalArgumentException(Errors.NUMBERS_HAS_DUPLICATE_NUMBER);
        }
    }

    private boolean hasDuplicates(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        return numberSet.size() != numbers.size();
    }

    private boolean isNumberInRange(int number) {
        return number >= MIN_NUMBER && number <= MAX_NUMBER;
    }

    private void validateLottoNumbersSize(List<Integer> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException(Errors.WRONG_LOTTO_SIZE);
        }
    }

    @Override
    public List<Integer> numbers() {
        Collections.sort(numbers);
        return Collections.unmodifiableList(numbers);
    }

    public int getMatchingNumberCount(List<Integer> comparingNumbers) {
        return numbers.stream()
            .filter(comparingNumbers::contains)
            .toList()
            .size();
    }

    public boolean isContains(int number) {
        return numbers.contains(number);
    }
}
