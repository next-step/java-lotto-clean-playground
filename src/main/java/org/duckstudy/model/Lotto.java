package org.duckstudy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Lotto {

    public static final int START_INCLUSIVE = 1;
    public static final int END_EXCLUSIVE = 45;
    public static final List<Integer> NUMBERS = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE).boxed().toList();
    public static final int FROM_INDEX = 0;
    public static final int LOTTO_SIZE = 6;

    public final List<Integer> lotto;

    public Lotto() {
        this.lotto = Collections.unmodifiableList(makeLotto());
    }

    private List<Integer> makeLotto() {
        List<Integer> numbers = new ArrayList<>(NUMBERS);

        Collections.shuffle(numbers);
        numbers = numbers.subList(FROM_INDEX, LOTTO_SIZE);

        Collections.sort(numbers);

        return numbers;
    }

    public List<Integer> getLotto() {
        return lotto;
    }
}
