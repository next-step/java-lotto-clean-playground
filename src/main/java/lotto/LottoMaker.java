package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMaker {
    private final List<LottoNumber> container = new ArrayList<>();

    public LottoMaker() {
        for (int n = 1; n <= 45; n++) {
            container.add(new LottoNumber(n));
        }
    }

    public Lotto makeLotto() {
        LottoNumbers numbers = makeLottoNumbers();
        return new Lotto(numbers);
    }

    public LottoNumbers makeLottoNumbers() {
        Collections.shuffle(container);
        List<LottoNumber> numbers = new ArrayList<>(container.subList(0, LottoNumbers.NUMBER_COUNT));
        Collections.sort(numbers);
        return new LottoNumbers(numbers);
    }
}
