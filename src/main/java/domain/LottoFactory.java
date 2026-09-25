package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    private final NumberGenerator numberGenerator;

    public LottoFactory(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Lottos create(int count) {
        List<Lotto> lottoList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottoList.add(createLotto());
        }

        return new Lottos(lottoList);
    }

    private Lotto createLotto() {
        return new Lotto(numberGenerator.generate());
    }
}