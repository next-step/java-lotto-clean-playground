package io.suhan.lotto.model;

import io.suhan.lotto.model.lotto.Lotto;
import java.util.ArrayList;
import java.util.List;

public class NumberPool {
    private final List<Integer> numbers;

    public NumberPool(int from, int to) {
        this.numbers = generateNumbersInRange(from, to);
    }

    private List<Integer> generateNumbersInRange(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException("from 값은 to 값보다 작아야 합니다.");
        }

        int size = to - from + 1;

        if (size < Lotto.LOTTO_SIZE) {
            throw new IllegalArgumentException("범위의 크기는 " + Lotto.LOTTO_SIZE + " 보다 커야 합니다.");
        }

        List<Integer> generated = new ArrayList<>();

        for (int i = from; i <= to; i++) {
            generated.add(i);
        }

        return generated;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
