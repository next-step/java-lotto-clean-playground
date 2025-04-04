package model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static utils.LottoConstants.*;
import static utils.LottoConstants.LOTTO_MIN_NUMBER;

public class LottoNumbersGenerator implements NumbersGenerator {

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
