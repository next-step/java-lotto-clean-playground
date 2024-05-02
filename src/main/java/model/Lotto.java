package model;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;
    public Lotto(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

}
