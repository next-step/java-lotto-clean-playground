package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private final static int LOTTO_SIZE = 6;

    private final NumberGenerator numberGenerator;

    private final List<Integer> lotto;

    public Lotto(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        this.lotto = createLotto();
    }

    private List<Integer> createLotto() {
        List<Integer> lotto = new ArrayList<>();
        for (int count = 0; count < LOTTO_SIZE; count++) {
            lotto.add(getLottoNumber(lotto));
        }
        Collections.shuffle(lotto);
        return lotto;
    }

    private int getLottoNumber(List<Integer> lotto) {
        while (true) {
            int randomNumber = numberGenerator.generate();
            if (!lotto.contains(randomNumber)) {
                return randomNumber;
            }
        }
    }

    @Override
    public String toString() {
        return lotto.toString();
    }
}
