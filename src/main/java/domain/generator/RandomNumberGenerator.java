package domain.generator;

import static domain.constant.LottoConstants.LOTTO_MAX_NUMBER;
import static domain.constant.LottoConstants.LOTTO_MIN_NUMBER;
import static domain.constant.LottoConstants.LOTTO_NUMBER_COUNT;

import domain.Number;
import domain.Numbers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public Numbers generate() {
        List<Integer> candidates = new ArrayList<>();
        for (int i = LOTTO_MIN_NUMBER; i <= LOTTO_MAX_NUMBER; i++) {
            candidates.add(i);
        }
        Collections.shuffle(candidates);

        List<Integer> numbers = candidates.subList(0, LOTTO_NUMBER_COUNT);
        return new Numbers(numbers.stream()
                .map(Number::new)
                .toList());
    }
}
