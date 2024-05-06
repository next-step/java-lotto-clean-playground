package org.duckstudy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Lotto {

    public static final List<Integer> numList = IntStream.range(1, 45).boxed().toList();

    public final List<Integer> lotto;

    public Lotto() {
        this.lotto = Collections.unmodifiableList(makeLotto());
    }

    private List<Integer> makeLotto() {
        List<Integer> lotto = new ArrayList<>(numList);
        Collections.shuffle(lotto);
        lotto = lotto.subList(0, 6);
        Collections.sort(lotto);
        return lotto;
    }

    public List<Integer> toList() {
        return lotto;
    }
}
