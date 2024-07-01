package org.duckstudy.domain.lotto;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.duckstudy.domain.lotto.LottoNumber.END_INCLUSIVE_NUMBER;
import static org.duckstudy.domain.lotto.LottoNumber.START_INCLUSIVE_NUMBER;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lotto {

    private static final Set<Integer> NUMBERS;
    private static final int LOTTO_SIZE = 6;

    static {
        NUMBERS = IntStream.range(START_INCLUSIVE_NUMBER, END_INCLUSIVE_NUMBER + 1)
                .boxed()
                .collect(toSet());
    }

    private final Set<LottoNumber> lotto;

    public Lotto(final Set<LottoNumber> lotto) {
        validateLottoSize(lotto);
        this.lotto = new HashSet<>(lotto);
    }

    public static Lotto from(final Set<Integer> values) {
        return new Lotto(values.stream()
                .map(LottoNumber::valueOf)
                .collect(toSet()));
    }

    public static Lotto createRandomLotto() {
        return new Lotto(NUMBERS.stream()
                .collect(getCollector())
                .limit(LOTTO_SIZE)
                .sorted()
                .map(LottoNumber::valueOf)
                .collect(toSet()));
    }

    private static Collector<Integer, ?, Stream<Integer>> getCollector() {
        return collectingAndThen(toList(),
                (List<Integer> list) -> {
                    shuffle(list);
                    return list.stream();
                });
    }

    public int countMatchingNumber(final Lotto compareLotto) {
        return lotto.stream()
                .filter(lottoNumber -> compareLotto.getLotto().contains(lottoNumber))
                .toList()
                .size();
    }

    public boolean containsNumber(final LottoNumber lottoNumber) {
        return lotto.contains(lottoNumber);
    }

    private void validateLottoSize(final Set<LottoNumber> lotto) {
        if (lotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(String.format("로또 번호는 중복되지 않은 %d개여야 합니다.\n", LOTTO_SIZE));
        }
    }

    public Set<LottoNumber> getLotto() {
        return Collections.unmodifiableSet(lotto);
    }
}
