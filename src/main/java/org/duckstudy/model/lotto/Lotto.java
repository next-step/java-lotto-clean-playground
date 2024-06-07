package org.duckstudy.model.lotto;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.duckstudy.model.lotto.constant.LottoBoundary.END_INCLUSIVE_NUMBER;
import static org.duckstudy.model.lotto.constant.LottoBoundary.LOTTO_SIZE;
import static org.duckstudy.model.lotto.constant.LottoBoundary.START_INCLUSIVE_NUMBER;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lotto {

    private static final List<Integer> NUMBERS;

    static {
        NUMBERS = IntStream.range(START_INCLUSIVE_NUMBER.getValue(), END_INCLUSIVE_NUMBER.getValue() + 1)
                .boxed()
                .toList();
    }

    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        validateLotto(lotto);
        this.lotto = Collections.unmodifiableList(lotto);
    }

    public Lotto(int... values) {
        this(IntStream.of(values)
                .mapToObj(LottoNumber::valueOf)
                .toList());
    }

    public static Lotto createRandomLotto() {
        return new Lotto(NUMBERS.stream()
                .collect(getCollector())
                .limit(LOTTO_SIZE.getValue())
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

    private void validateLotto(List<LottoNumber> lotto) {
        if (lotto.size() != LOTTO_SIZE.getValue()) {
            throw new IllegalArgumentException(String.format("로또 번호는 %d개여야 합니다.", LOTTO_SIZE.getValue()));
        }

        if (lotto.stream().distinct().count() != LOTTO_SIZE.getValue()) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }

    public List<LottoNumber> getLotto() {
        return lotto;
    }
}
