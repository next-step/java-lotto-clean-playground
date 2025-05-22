package domain.lotto;

import static domain.lotto.LottoConstant.LOTTO_NUMBER_COUNT;

import java.util.Collections;
import java.util.List;
import strategy.LottoNumberGenerator;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(final List<LottoNumber> numbers) {
        validateLottoCount(numbers);
        validateDuplicateLottoNumber(numbers);
        this.numbers = numbers;
    }

    public static Lotto create(final LottoNumberGenerator generator) {
        return new Lotto(generator.generate());
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validateLottoCount(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 %s개여야 합니다.".formatted(LOTTO_NUMBER_COUNT));
        }
    }

    private void validateDuplicateLottoNumber(final List<LottoNumber> numbers) {
        boolean isDuplicate = numbers.stream().distinct().count() != LOTTO_NUMBER_COUNT;
        if (isDuplicate) {
            throw new IllegalArgumentException("중복된 로또 번호가 있습니다.");
        }
    }

    public int countMatch(final Lotto other) {
        return (int) this.numbers.stream()
                .filter(other.numbers::contains)
                .count();
    }
}
