package io.suhan.lotto.model.lotto;

import io.suhan.lotto.model.NumberPool;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoFactory {
    public static Lotto createLotto(LottoType type) {
        NumberPool pool = NumberPool.of(Lotto.LOTTO_NUMBER_MIN, Lotto.LOTTO_NUMBER_MAX);
        return createLotto(type, pool);
    }

    public static Lotto createLotto(LottoType type, NumberPool pool) {
        List<Integer> poolNumbers = new ArrayList<>(pool.getNumbers()); // copy
        Collections.shuffle(poolNumbers);

        Set<LottoNumber> numbers = poolNumbers.subList(0, Lotto.LOTTO_SIZE)
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());

        return Lotto.of(type, numbers);
    }

    public static Set<LottoNumber> toLottoNumbers(Set<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
    }

    public static int getManualLottosCount(List<Lotto> lottos) {
        return lottos.stream().filter((lotto) -> lotto.getType() == LottoType.MANUAL).toList().size();
    }
}
