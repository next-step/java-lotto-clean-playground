package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumbers {
    private static final int REQUIRED_SIZE = 6;
    private static final String ERROR_INVALID_SIZE = "[ERROR] 로또 번호는 6개여야 합니다.";
    private static final String ERROR_DUPLICATED = "[ERROR] 로또 번호는 중복될 수 없습니다.";

    private final List<LottoNumber> numbers;

    public LottoNumbers(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public boolean containsValue(int value) {
        return numbers.stream()
                .anyMatch(number -> number.value() == value);
    }

    private void validate(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException(ERROR_INVALID_SIZE);
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        Set<LottoNumber> unique = new HashSet<>(numbers);
        if (unique.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException(ERROR_DUPLICATED);
        }
    }
}
