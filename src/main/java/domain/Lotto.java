package domain;

import java.util.Collections;
import java.util.List;
import util.Errors;

public class Lotto {

    public final static int SIZE = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoNumbersSize(numbers);
        this.numbers = numbers;
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
