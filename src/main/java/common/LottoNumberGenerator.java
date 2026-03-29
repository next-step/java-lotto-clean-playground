package common;

import constants.LOTTO_SETTINGS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.min;

public class LottoNumberGenerator implements NumberGenerator {
    private final List<Integer> numbers;
    private int index = 0;

    LottoNumberGenerator() {
        this.numbers = new ArrayList<Integer>();
        for (int i = LOTTO_SETTINGS.LOTTO_MINIMUM_NUMBER; i <= LOTTO_SETTINGS.LOTTO_MAXIMUM_NUMBER; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);
    }

    @Override
    public int generateNumber() {
        if (index >= min(LOTTO_SETTINGS.LOTTO_SIZE, LOTTO_SETTINGS.LOTTO_RANGE)) {
            index = 0;
            Collections.shuffle(numbers);
        }

        return numbers.get(index++);
    }
}
