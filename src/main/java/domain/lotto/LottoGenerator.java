package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoGenerator {
    private static final List<LottoNumber> NUMBERS = IntStream.
            rangeClosed(LottoNumber.MIN_NUMBER, LottoNumber.MAX_NUMBER)
            .boxed()
            .map(LottoNumber::from)
            .toList();

    private LottoGenerator() {}

    public static Lotto generateLotto() {
        List<LottoNumber> shuffledNumbers = new ArrayList<>(NUMBERS);
        Collections.shuffle(shuffledNumbers);
        return Lotto.from(shuffledNumbers);
    }
}
