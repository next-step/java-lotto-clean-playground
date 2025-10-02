package domain;

import java.util.HashSet;
import java.util.Set;

public class createList {
    public static final int LOTTO_MIN_NUMBER = 1;
    public static final int LOTTO_MAX_NUMBER = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;

    public static Set<Integer> generateLottoNumbers() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < LOTTO_NUMBER_COUNT) {
            int num = (int) (Math.random() * LOTTO_MAX_NUMBER) + LOTTO_MIN_NUMBER;
            numbers.add(num);
        }
        return numbers;
    }
}
