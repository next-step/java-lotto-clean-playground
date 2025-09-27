package io.suhan.lotto.model.lotto;

import io.suhan.lotto.model.NumberPool;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoFactory {
    public static Lotto createLotto() {
        NumberPool pool = NumberPool.of(Lotto.LOTTO_NUMBER_MIN, Lotto.LOTTO_NUMBER_MAX);
        return createLotto(pool);
    }

    private static Lotto createLotto(NumberPool pool) {
        List<Integer> poolNumbers = new ArrayList<>(pool.getNumbers()); // copy
        Collections.shuffle(poolNumbers);

        Set<LottoNumber> numbers = poolNumbers.subList(0, Lotto.LOTTO_SIZE)
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());

        return new Lotto(numbers);
    }
}
