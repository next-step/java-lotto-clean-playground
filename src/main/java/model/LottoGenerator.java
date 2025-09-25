package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final List<Integer> NUMBER_POOL = createNumberPool();

    private static List<Integer> createNumberPool() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = LottoNumber.MIN_LOTTO_NUMBER; i <= LottoNumber.MAX_LOTTO_NUMBER; i++) {
            numbers.add(i);
        }
        return Collections.unmodifiableList(numbers);
    }

    public static List<Integer> generate() {
        List<Integer> numbersToShuffle = new ArrayList<>(NUMBER_POOL);
        Collections.shuffle(numbersToShuffle);

        List<Integer> pickedNumbers = new ArrayList<>(numbersToShuffle.subList(0, Lotto.LOTTO_TICKET_SIZE));
        Collections.sort(pickedNumbers);

        return pickedNumbers;
    }
}
