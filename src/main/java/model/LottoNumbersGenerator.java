package model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LottoNumbersGenerator implements NumbersGenerator {

    private static final int LOTTO_MAX_NUMBER = 45;

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private final ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    @Override
    public List<Integer> generate() {

        TreeSet<Integer> numbers = new TreeSet<>();
        while(numbers.size() < LOTTO_NUMBER_COUNT) {
            int number = threadLocalRandom.nextInt(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER + 1);
            numbers.add(number);
        }

        return new ArrayList<>(numbers);
    }
}
