package lotto;

import static lotto.Constants.LOTTO_NUMBERS_COUNT;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private final Collection<LottoNumber> numbers;

    public Lotto(final Collection<Integer> numbers) {
        this(
            numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet())
        );
    }

    public Lotto(final Set<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_COUNT) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_NUMBERS_COUNT + "개여야 함");
        }

        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers.toArray());
    }

}
