package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public static List<Integer> generateNumbers() {
        List<Integer> numbers = createNumbers();

        Collections.shuffle(numbers);
        List<Integer> lottoNumbers = new ArrayList<>(numbers.subList(0, LOTTO_NUMBER_COUNT));

        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

    private static List<Integer> createNumbers() {
        List<Integer> numbers = new ArrayList<>();

        for (int number = MINIMUM_LOTTO_NUMBER; number <= MAXIMUM_LOTTO_NUMBER; number++) {
            numbers.add(number);
        }
        return numbers;
    }

}


