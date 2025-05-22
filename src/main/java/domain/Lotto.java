package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int PRICE = 1000;
    private static final int REQUIRED_SIZE = 6;
    private static final String ERROR_INVALID_SIZE = "[ERROR] 로또 번호는 6개여야 합니다.";
    private static final String ERROR_DUPLICATED = "[ERROR] 로또 번호는 중복될 수 없습니다.";

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
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
        if (numbers.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException(ERROR_INVALID_SIZE);
        }
        Set<LottoNumber> unique = new HashSet<>(numbers);
        if (unique.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException(ERROR_DUPLICATED);
        }
    }
}
