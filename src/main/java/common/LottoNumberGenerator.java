package common;

import constants.LottoSettingsConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.min;

public class LottoNumberGenerator implements NumberGenerator {
    private final List<Integer> numbers;
    private int index = 0;

    public LottoNumberGenerator() {
        this.numbers = new ArrayList<Integer>();
        for (int i = LottoSettingsConstants.LOTTO_MINIMUM_NUMBER; i <= LottoSettingsConstants.LOTTO_MAXIMUM_NUMBER; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);
    }

    @Override
    public int generateNumber() {
        if (index >= min(LottoSettingsConstants.LOTTO_SIZE, LottoSettingsConstants.LOTTO_RANGE)) {
            index = 0;
            Collections.shuffle(numbers);
        }

        return numbers.get(index++);
    }
}
