package domain;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LottoNumbers {
    private static final int SIZE = 6;
    private final List<LottoNumber> numbers;

    public LottoNumbers(List<LottoNumber> numbers) {
        validateSize(numbers);
        this.numbers = numbers;
    }

    public void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() > SIZE) {
            throw new IllegalArgumentException();
        }
    }
}
