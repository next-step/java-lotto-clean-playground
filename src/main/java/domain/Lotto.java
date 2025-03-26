package domain;

import java.util.Collections;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(LottoNumberGenerator lottoNumberGenerator) {
        this.numbers = lottoNumberGenerator.generate();
    }

    public Integer calculateMatchCount(List<Integer> winningNumbers) {
        Integer matchCount;
        return matchCount = Math.toIntExact(numbers.stream()
                .filter(winningNumbers::contains)
                .count());
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
