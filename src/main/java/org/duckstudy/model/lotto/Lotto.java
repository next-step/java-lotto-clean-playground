package org.duckstudy.model.lotto;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.duckstudy.model.lotto.constant.LottoNumberRange.END_INCLUSIVE_NUMBER;
import static org.duckstudy.model.lotto.constant.LottoNumberRange.START_INCLUSIVE_NUMBER;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lotto {

    private static final List<Integer> NUMBERS;
    private static final int LOTTO_SIZE = 6;

    static {
        NUMBERS = IntStream.range(START_INCLUSIVE_NUMBER.getValue(), END_INCLUSIVE_NUMBER.getValue() + 1)
                .boxed()
                .toList();
    }

    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        validateLottoSize(lotto);
        validateDuplicate(lotto);
        this.lotto = Collections.unmodifiableList(lotto);
    }

    public static Lotto from(List<Integer> values) {
        return new Lotto(values.stream()
                .map(LottoNumber::valueOf)
                .collect(toList()));
    }

    public static Lotto createRandomLotto() {
        return new Lotto(NUMBERS.stream()
                .collect(getCollector())
                .limit(LOTTO_SIZE)
                .sorted()
                .map(LottoNumber::valueOf)
                .collect(toList()));
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

    private void validateLottoSize(List<LottoNumber> lotto) {
        if (lotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(String.format("로또 번호는 %d개여야 합니다.", LOTTO_SIZE));
        }
    }

    private void validateDuplicate(List<LottoNumber> lotto) {
        if (lotto.stream().distinct().count() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }

    public List<LottoNumber> getLotto() {
        return lotto;
    }
}
