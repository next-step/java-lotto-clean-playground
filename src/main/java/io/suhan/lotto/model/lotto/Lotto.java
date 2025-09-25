package io.suhan.lotto.model.lotto;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Lotto {
    public static final int LOTTO_SIZE = 6;
    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;

    private final Set<LottoNumber> numbers;

    public Lotto(Set<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_SIZE + "개여야 합니다.");
        }

        this.numbers = new HashSet<>(numbers);
    }

    public Set<LottoNumber> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.stream().sorted(Comparator.comparingInt(LottoNumber::getValue)).toList().toString();
    }
}
