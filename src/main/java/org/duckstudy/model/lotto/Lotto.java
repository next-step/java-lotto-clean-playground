package org.duckstudy.model.lotto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    private static final int START_INCLUSIVE_NUMBER = 1;
    private static final int END_EXCLUSIVE_NUMBER = 46;
    private static final List<Integer> NUMBERS;

    private final List<Integer> lotto;

    static {
        NUMBERS = IntStream.range(START_INCLUSIVE_NUMBER, END_EXCLUSIVE_NUMBER)
                .boxed()
                .toList();
    }

    private Lotto(List<Integer> lotto) {
        this.lotto = Collections.unmodifiableList(lotto);
    }

    public static Lotto createRandomLotto() {
        List<Integer> result = NUMBERS.stream()
                .sorted(Comparator.comparingInt(i -> ThreadLocalRandom.current().nextInt()))
                .limit(6)
                .sorted()
                .collect(Collectors.toList());

        return new Lotto(result);
    }

    public static Lotto createManualLotto(List<Integer> lotto) {
        return new Lotto(lotto);
    }

    public List<Integer> getLotto() {
        return lotto;
    }
}
