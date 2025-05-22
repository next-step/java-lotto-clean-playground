package strategy;

import static domain.lotto.LottoConstant.LOTTO_NUMBER_COUNT;
import static domain.lotto.LottoConstant.MAX_LOTTO_NUMBER;
import static domain.lotto.LottoConstant.MIN_LOTTO_NUMBER;

import domain.lotto.LottoNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements LottoNumberGenerator {

    @Override
    public List<LottoNumber> generate() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, LOTTO_NUMBER_COUNT)
                .stream()
                .map(LottoNumber::new)
                .sorted()
                .toList();
    }
}
