package model;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int LOTTO_SIZE = 6;


    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public static void validate(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new RuntimeException("로또 번호는 6개여야 합니다.");
        }
        if (lottoNumbers.stream().anyMatch(n -> n < MIN_LOTTO_NUMBER || n > MAX_LOTTO_NUMBER)) {
            throw new RuntimeException("로또 번호는 1이상 45이하여야 합니다.");
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
