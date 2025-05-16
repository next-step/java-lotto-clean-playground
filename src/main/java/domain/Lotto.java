package domain;

import static domain.LottoConstant.LOTTO_NUMBER_COUNT;
import static domain.LottoConstant.MAX_LOTTO_NUMBER;
import static domain.LottoConstant.MIN_LOTTO_NUMBER;

import java.util.Collections;
import java.util.List;
import strategy.LottoNumberGenerator;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public static Lotto create(LottoNumberGenerator generator) {
        return new Lotto(generator.generate());
    }

    private void validate(List<Integer> numbers) {
        validateLottoCount(numbers);
        validateDuplicateLottoNumber(numbers);
        validateLottoRange(numbers);
    }

    private void validateLottoCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicateLottoNumber(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("중복된 로또 번호가 있습니다.");
        }
    }

    private void validateLottoRange(List<Integer> numbers) {
        for (int num : numbers) {
            if (num < MIN_LOTTO_NUMBER || MAX_LOTTO_NUMBER < num) {
                throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이의 값이어야 합니다.");
            }
        }
    }
}
