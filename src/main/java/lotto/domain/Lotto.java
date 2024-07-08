package lotto.domain;

import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto create(NumberGenerator numberGenerator) {
        List<Integer> generate = numberGenerator.generate();
        return new Lotto(generate);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
