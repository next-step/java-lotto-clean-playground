package model;

import java.util.Collections;
import java.util.List;

public class Lotto {

    private final NumbersGenerator numbersGenerator;
    private final List<Integer> numbers;

    public Lotto(NumbersGenerator numbersGenerators) {
        this.numbersGenerator = numbersGenerators;
        this.numbers = numbersGenerator.generate();
    }

    public int size() {
        return numbers.size();
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
