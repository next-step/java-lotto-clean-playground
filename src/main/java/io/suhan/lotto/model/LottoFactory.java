package io.suhan.lotto.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoFactory {
    public static Lotto createLotto(NumberPool pool) {
        Set<LottoNumber> numbers = new HashSet<>();
        List<Integer> poolNumbers = pool.getNumbers();

        while (numbers.size() < Lotto.LOTTO_SIZE) {
            Collections.shuffle(poolNumbers);
            numbers.add(new LottoNumber(poolNumbers.get(0)));
        }

        return new Lotto(numbers);
    }

    public static Lotto createLotto() {
        NumberPool pool = new NumberPool(LottoNumber.LOTTO_NUMBER_MIN, LottoNumber.LOTTO_NUMBER_MAX);
        return createLotto(pool);
    }
}
