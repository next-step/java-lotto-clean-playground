package model;

import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_MIN = 1;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("로또 번호 목록이 비어 있거나 null 입니다.");
        }
        validateNumbers(numbers);
        this.numbers = Collections.unmodifiableList(numbers);
    }

    private void validateNumbers(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("Number size must be equal to " + LOTTO_NUMBER_SIZE);
        }

        for (int number : numbers) {
            validateNumber(number);
        }
    }

    private void validateNumber(int number) {
        if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("Number must be between " + LOTTO_NUMBER_MIN + " and " + LOTTO_NUMBER_MAX);
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
