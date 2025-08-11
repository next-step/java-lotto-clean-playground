package model;

import java.util.List;

public class Lotto {
    private final LottoNumbers numbers;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int LOTTO_SIZE = 6;

    public Lotto(List<Integer> numbers) {
        this.numbers = new LottoNumbers(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers.asList();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
