package domain;

import domain.generator.NumberGenerator;

public class Lotto {
    private final Numbers numbers;

    public Lotto(NumberGenerator generator) {
        this.numbers = generator.generate();
    }

    public Numbers getNumbers() {
        return numbers;
    }
}
