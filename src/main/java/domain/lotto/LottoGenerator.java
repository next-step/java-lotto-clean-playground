package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private LottoGenerator() {}

    public static Lotto generateLotto() {
        List<LottoNumber> shuffledNumbers = new ArrayList<>(LottoNumber.values());
        Collections.shuffle(shuffledNumbers);
        return Lotto.from(shuffledNumbers);
    }
}
