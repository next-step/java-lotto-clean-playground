package domain;

import domain.generator.NumberGenerator;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(NumberGenerator generator) {
        this.numbers = generator.generate();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
