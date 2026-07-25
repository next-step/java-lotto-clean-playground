package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    private List<Integer> generateNumbers() {
        List<Integer> allNumbers = new ArrayList<>();
        for (int i = Lotto.MIN_LOTTO_NUMBER; i <= Lotto.MAX_LOTTO_NUMBER; i++) {
            allNumbers.add(i);
        }

        return allNumbers;
    }

    public Lotto generateLotto() {
        List<Integer> numbers = generateNumbers();
        Collections.shuffle(numbers);

        List<Integer> selected = new ArrayList<>(numbers.subList(0, Lotto.LOTTO_SIZE));
        Collections.sort(selected);

        return new Lotto(selected);
    }
}
