package domain;

import static domain.constant.LottoConstants.LOTTO_MAX_NUMBER;
import static domain.constant.LottoConstants.LOTTO_MIN_NUMBER;
import static domain.constant.LottoConstants.LOTTO_NUMBER_COUNT;

import java.util.ArrayList;
import java.util.List;

public class Numbers {
    public static final String ERROR_INVALID_SIZE = "로또 번호는 " + LOTTO_NUMBER_COUNT + "개여야 합니다.";
    public static final String ERROR_OUT_OF_RANGE =
            "로또 번호는 " + LOTTO_MIN_NUMBER + "부터 " + LOTTO_MAX_NUMBER + " 사이여야 합니다.";
    public static final String ERROR_DUPLICATION = "로또 번호는 중복될 수 없습니다.";
    private final List<Integer> numbers;

    public Numbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private static void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplication(numbers);
    }

    private static void validateRange(List<Integer> numbers) {
        if (numbers.stream()
                .anyMatch(n -> n < LOTTO_MIN_NUMBER || n > LOTTO_MAX_NUMBER)) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_INVALID_SIZE);
        }
    }

    private static void validateDuplication(List<Integer> numbers) {
        if (numbers.stream()
                .distinct()
                .count() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_DUPLICATION);
        }
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }
}
