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
        Collections.shuffle(container);
        List<LottoNumber> numbers = new ArrayList<>(container.subList(0, 6));
        return new Lotto(numbers);
    }
}
