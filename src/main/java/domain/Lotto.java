package domain;

import static domain.constant.LottoConstants.LOTTO_NUMBER_COUNT;

import domain.generator.NumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class Lotto {
    public static final String ERROR_INVALID_SIZE = "로또 번호는 " + LOTTO_NUMBER_COUNT + "개여야 합니다.";
    public static final String ERROR_DUPLICATION = "로또 번호는 중복될 수 없습니다.";
    private final List<LottoNumber> numbers;

    public Lotto(NumberGenerator generator) {
        this(generator.generate());
    }

    public Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validate(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_INVALID_SIZE);
        }
    }

    private void validateDuplication(List<LottoNumber> numbers) {
        if (numbers.stream()
                .distinct()
                .count() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_DUPLICATION);
        }
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public int countMatch(Lotto other) {
        return (int) numbers.stream()
                .filter(other::contains)
                .count();
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }
}
