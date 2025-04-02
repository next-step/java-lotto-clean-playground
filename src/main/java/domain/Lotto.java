package domain;

import java.util.Collections;
import java.util.List;

public record Lotto(List<LottoNumber> numbers) {

    private static final int REQUIRED_NUMBERS = 6;

    public Lotto {
        validate(numbers);
    }

    private void validate(List<LottoNumber> numbers) {
        validateNumberCount(numbers);
        validateDuplicates(numbers);
    }

    private void validateNumberCount(List<LottoNumber> numbers) {
        if (numbers.size() != REQUIRED_NUMBERS) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicates(List<LottoNumber> numbers) {
        long distinctCount = numbers.stream()
                .map(LottoNumber::number)
                .distinct()
                .count();

        if (distinctCount != numbers.size()) {
            throw new IllegalArgumentException("중복된 번호가 있습니다.");
        }
    }

    public Integer calculateMatchCount(List<LottoNumber> winningNumbers) {
        return Math.toIntExact(numbers.stream()
                .filter(winningNumbers::contains)
                .count());
    }

    @Override
    public List<LottoNumber> numbers() {
        return Collections.unmodifiableList(numbers);
    }
}
