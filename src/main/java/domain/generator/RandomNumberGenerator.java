package domain.generator;

import static domain.constant.LottoConstants.LOTTO_MAX_NUMBER;
import static domain.constant.LottoConstants.LOTTO_MIN_NUMBER;
import static domain.constant.LottoConstants.LOTTO_NUMBER_COUNT;

import domain.LottoNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public List<LottoNumber> generate() {
        List<Integer> candidates = new ArrayList<>();
        for (int i = LOTTO_MIN_NUMBER; i <= LOTTO_MAX_NUMBER; i++) {
            candidates.add(i);
        }
        Collections.shuffle(candidates);

        return candidates.stream()
                .limit(LOTTO_NUMBER_COUNT)
                .map(LottoNumber::new)
                .toList();
    }
}
