package model;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private List<Integer> numbers = new ArrayList<>();
    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
