package domain;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

}
