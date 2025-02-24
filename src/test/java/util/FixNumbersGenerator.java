package util;

import domain.NumbersGenerator;

import java.util.List;

public class FixNumbersGenerator implements NumbersGenerator {

    private final List<Integer> numbers;

    public FixNumbersGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Integer> getNumbers() {
        return numbers;
    }
}
