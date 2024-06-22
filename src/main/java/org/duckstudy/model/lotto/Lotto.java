package org.duckstudy.model.lotto;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.duckstudy.model.lotto.LottoNumber.END_INCLUSIVE_NUMBER;
import static org.duckstudy.model.lotto.LottoNumber.START_INCLUSIVE_NUMBER;

import java.util.Collections;
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

    public Lotto(Set<LottoNumber> lotto) {
        validateLottoSize(lotto);
        this.lotto = Collections.unmodifiableSet(lotto);
    }

    public static Lotto from(Set<Integer> values) {
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

    public int countMatchingNumber(Lotto compareLotto) {
        return lotto.stream()
                .filter(lottoNumber -> compareLotto.getLotto().contains(lottoNumber))
                .toList()
                .size();
    }

    public boolean containsNumber(LottoNumber lottoNumber) {
        return lotto.contains(lottoNumber);
    }

    private void validateLottoSize(Set<LottoNumber> lotto) {
        if (lotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(String.format("로또 번호는 중복되지 않은 %d개여야 합니다.\n", LOTTO_SIZE));
        }
    }

    public Set<LottoNumber> getLotto() {
        return lotto;
    }
}
