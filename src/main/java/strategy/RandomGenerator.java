package strategy;

import static domain.LottoConstant.LOTTO_NUMBER_COUNT;
import static domain.LottoConstant.MAX_LOTTO_NUMBER;
import static domain.LottoConstant.MIN_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomGenerator implements LottoNumberGenerator {

    @Override
    public List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, LOTTO_NUMBER_COUNT);
    }
}
