package domain;

import static domain.LottoConstant.LOTTO_NUMBER_COUNT;

import java.util.Collections;
import java.util.List;
import strategy.LottoNumberGenerator;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(final List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static Lotto create(final LottoNumberGenerator generator) {
        return new Lotto(generator.generate());
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validate(final List<LottoNumber> numbers) {
        validateLottoCount(numbers);
        validateDuplicateLottoNumber(numbers);
    }

    private void validateLottoCount(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicateLottoNumber(final List<LottoNumber> numbers) {
        if (numbers.stream().distinct().count() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("중복된 로또 번호가 있습니다.");
        }
    }

    public int countMatch(Lotto other) {
        return (int) this.numbers.stream()
                .filter(other.numbers::contains)
                .count();
    }
}
