package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningLotto {
    private static final int REQUIRED_SIZE = 6;
    private static final String ERROR_INVALID_SIZE = "[ERROR] 당첨 번호는 6개여야 합니다.";
    private static final String ERROR_DUPLICATED = "[ERROR] 당첨 번호는 중복될 수 없습니다.";

    private final List<LottoNumber> numbers;

    public WinningLotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public int countMatch(Lotto lotto) {
        Set<Integer> winningValues = new HashSet<>();
        for (LottoNumber number : numbers) {
            winningValues.add(number.value());
        }

        long count = lotto.getNumbers().stream()
                .map(LottoNumber::value)
                .filter(winningValues::contains)
                .count();

        return (int) count;
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
