package model;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    private final Numbers numbers;

    public Lotto(final Numbers numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

}
