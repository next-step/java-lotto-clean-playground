package org.duckstudy.model.lotto;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Lotto {

    private static final int START_INCLUSIVE_NUMBER = 1;
    private static final int END_EXCLUSIVE_NUMBER = 46;
    private static final List<Integer> NUMBERS;
    public static final int LOTTO_SIZE = 6;

    static {
        NUMBERS = IntStream.range(START_INCLUSIVE_NUMBER, END_EXCLUSIVE_NUMBER)
                .boxed()
                .toList();
    }

    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        validateLotto(lotto);
        this.lotto = Collections.unmodifiableList(lotto);
    }

    public static Lotto createRandomLotto() {
        List<LottoNumber> result = NUMBERS.stream()
                .collect(collectingAndThen(toList(),
                        list -> {
                            shuffle(list);
                            return list.stream();
                        }))
                .limit(6)
                .sorted()
                .map(LottoNumber::valueOf)
                .collect(toList());

        return new Lotto(result);
    }

    public int countMatchingNumber(Lotto compareLotto) {
        return lotto.stream()
                .filter(compareLotto.getLotto()::contains)
                .toList()
                .size();
    }

    private void validateLotto(List<LottoNumber> lotto) {
        if (lotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }

        if (lotto.stream().distinct().count() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }

    public List<LottoNumber> getLotto() {
        return lotto;
    }
}
