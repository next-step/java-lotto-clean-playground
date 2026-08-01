package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final List<LottoNumber> NUMBERS = LottoNumber.values();

    private LottoGenerator() {}

    public static Lotto generateLotto() {
        List<LottoNumber> shuffledNumbers = new ArrayList<>(NUMBERS);
        Collections.shuffle(shuffledNumbers);
        return Lotto.from(shuffledNumbers);
    }
}
