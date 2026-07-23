package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumberRange(numbers);
        validateDuplicateNumber(numbers);
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(this::isOutOfRange)) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이여야 합니다.");
        }
    }

    private boolean isOutOfRange(int number) {
        return number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER;
    }

    private void validateDuplicateNumber(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
