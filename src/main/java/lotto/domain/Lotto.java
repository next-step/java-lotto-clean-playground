package lotto.domain;

import java.util.List;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers.stream()
            .map(LottoNumber::new)
            .toList();
    }

    public static Lotto create(NumberGenerator numberGenerator) {
        List<Integer> generate = numberGenerator.generate();
        return new Lotto(generate);
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
            .map(LottoNumber::getNumber)
            .toList();
    }
}
