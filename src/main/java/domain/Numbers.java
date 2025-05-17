package domain;

import static domain.constant.LottoConstants.LOTTO_NUMBER_COUNT;

import java.util.ArrayList;
import java.util.List;

public class Numbers {
    public static final String ERROR_INVALID_SIZE = "로또 번호는 " + LOTTO_NUMBER_COUNT + "개여야 합니다.";
    public static final String ERROR_DUPLICATION = "로또 번호는 중복될 수 없습니다.";
    private final List<Number> numbers;

    public Numbers(List<Number> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validate(List<Number> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateSize(List<Number> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_INVALID_SIZE);
        }
    }

    private void validateDuplication(List<Number> numbers) {
        if (numbers.stream()
                .distinct()
                .count() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_DUPLICATION);
        }
    }

    public List<Number> getNumbers() {
        return new ArrayList<>(numbers);
    }
}
