package model;

import common.ValidateLotto;

import java.util.ArrayList;
import java.util.List;

public record Lotto(List<Integer> numbers) {
    public Lotto(List<Integer> numbers) {
       ValidateLotto.checkIfNumbersAreValid(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    public List<Integer> numbers() {
        return List.copyOf(this.numbers);
    }
}
