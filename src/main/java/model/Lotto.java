package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private static final int NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;
    private final LottoType type;

    public Lotto(List<LottoNumber> numbers, LottoType type) {
        validate(numbers);
        this.numbers = numbers;
        this.type = type;
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }

        Set<LottoNumber> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public long countMatch(Lotto winningLotto) {
        return numbers.stream()
                .filter(winningLotto.getNumbers()::contains)
                .count();
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public LottoType getType() {
        return type;
    }
}
