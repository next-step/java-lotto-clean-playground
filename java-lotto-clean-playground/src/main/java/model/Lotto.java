package model;

import java.util.List;

public class Lotto {
    private static final int BONUS_NUMBER_INDEX = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer>getNumbers(){
        return numbers;
    }
}