package domain;

import java.util.Collections;
import java.util.List;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(LottoNumberGenerator generator) {
        this.numbers = generator.generate();
    }

    public Integer calculateMatchCount(List<LottoNumber> winningNumbers) {
        return Math.toIntExact(numbers.stream()
                .filter(winningNumbers::contains)
                .count());
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
