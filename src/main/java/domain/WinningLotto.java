package domain;

import static domain.constant.LottoConstants.*;

import java.util.ArrayList;
import java.util.List;

public class WinningLotto {
    private static final String ERROR_INVALID_SIZE = "로또 번호는 " + LOTTO_NUMBER_COUNT + "개여야 합니다.";
    private static final String ERROR_OUT_OF_RANGE =
            "로또 번호는 " + LOTTO_MIN_NUMBER + "부터 " + LOTTO_MAX_NUMBER + " 사이여야 합니다.";
    private final List<Integer> numbers;

    public WinningLotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_INVALID_SIZE);
        }

        if (numbers.stream()
                .anyMatch(n -> n < LOTTO_MIN_NUMBER || n > LOTTO_MAX_NUMBER)) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
