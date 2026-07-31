package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Lotto {
    private static final int NUMBER_COUNT = 6;
    private static final int RANDOM_NUMBER_BOUND = 45;

    private final Random random = new Random();
    private final List<LottoNumber> numbers = new ArrayList<>();

    public Lotto() {
        makeNumbers();
    }

    public List<LottoNumber> getNumbers() {
        return List.copyOf(numbers);
    }

    private void makeNumbers() {
        for (int i = 0; i < NUMBER_COUNT; i++) {
            numbers.add(generateRandomNumber());
        }

        Collections.sort(numbers);
    }

    private LottoNumber generateRandomNumber() {
        LottoNumber lottoNumber;

        do {
            lottoNumber = new LottoNumber(random.nextInt(RANDOM_NUMBER_BOUND) + 1);
        } while (containsNumber(lottoNumber));

        return lottoNumber;
    }

    private boolean containsNumber(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }
}
