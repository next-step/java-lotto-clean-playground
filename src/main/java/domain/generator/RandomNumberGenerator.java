package domain.generator;

import static domain.constant.LottoConstants.LOTTO_MAX_NUMBER;
import static domain.constant.LottoConstants.LOTTO_MIN_NUMBER;
import static domain.constant.LottoConstants.LOTTO_NUMBER_COUNT;

import domain.Numbers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public Numbers generate() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = LOTTO_MIN_NUMBER; i <= LOTTO_MAX_NUMBER; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return new Numbers(numbers.subList(0, LOTTO_NUMBER_COUNT));
    }
}
