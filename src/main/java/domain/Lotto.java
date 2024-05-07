package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Lotto(List<LottoNumber> numbers) {
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final int LOTTO_PRICE = 1000;

    public Lotto {
        validateNumbers(numbers);
        Collections.sort(numbers);
    }

    private void validateNumbers(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 숫자는 6개여야 합니다.");
        }
        if (numbers.stream().distinct().count() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 숫자는 중복 되지 않아야 합니다.");
        }
    }

    public static Lotto generate() {
        List<LottoNumber> numbers = Stream.generate(LottoNumber::generate)
                .distinct()
                .limit(LOTTO_NUMBER_COUNT)
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    public boolean contains(final LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
