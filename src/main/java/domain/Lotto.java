package domain;

import java.util.Collections;
import java.util.List;
import util.Errors;

public class Lotto {

    public final static int SIZE = 6;
    public final static int MIN_NUMBER = 1;
    public final static int MAX_NUMBER = 45;
    public final static int PRICE = 1000;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    private void validateNumbers(List<Integer> numbers) {
        validateNumbersRange(numbers);
        validateLottoNumbersSize(numbers);
    }

    private void validateNumbersRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (!isNumberInRange(number)) {
                throw new IllegalArgumentException(Errors.NUMBER_IS_NOT_IN_VALID_RANGE);
            }
        }
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
    public String toString() {
        Collections.sort(numbers);
        return numbers.toString();
    }
}
