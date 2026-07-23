package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int LOTTO_SIZE = 6;

    private List<Integer> generateNumbers() {
        List<Integer> allNumbers = new ArrayList<>();
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            allNumbers.add(i);
        }

        return allNumbers;
    }

    public List<Integer> generateLotto() {
        List<Integer> numbers = generateNumbers();
        Collections.shuffle(numbers);

        List<Integer> lotto = new ArrayList<>(numbers.subList(0, LOTTO_SIZE));
        Collections.sort(lotto);

        return lotto;
    }
}
