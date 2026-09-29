package domain;

import domain.purchase.LottoNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {

    List<LottoNumber> lotto = new ArrayList<>();

    public List<LottoNumber> generate() {
        lotto = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lotto.add(new LottoNumber(i));
        }

        Collections.shuffle(lotto);

        lotto = new ArrayList<>(lotto.subList(0, 6));

        Collections.sort(lotto);

        return lotto;
    }
}
