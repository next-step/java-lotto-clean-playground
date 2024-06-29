package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoNumberGenerator {

    private static final List<Integer> NUMBERS = IntStream.rangeClosed(Lotto.MIN_NUMBER, Lotto.MAX_NUMBER)
        .boxed()
        .toList();

    public List<Integer> generate() {
        List<Integer> lottoNumbers = new ArrayList<>(NUMBERS);
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(0, Lotto.SIZE);
    }

}
