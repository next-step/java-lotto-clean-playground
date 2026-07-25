package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Numbers {

    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    protected Numbers(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        this.numbers = toLottoNumbers(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(label() + "는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> unique = new HashSet<>(numbers);
        if (unique.size() != numbers.size()) {
            throw new IllegalArgumentException(label() + "는 중복될 수 없습니다.");
        }
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    protected abstract String label();

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toList());
    }
}
