package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private final LottoNumberGenerator generator;

    public LottoMachine(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public List<Lotto> purchase(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();

        for (int count = 0; count < lottoCount; count++) {
            lottos.add(new Lotto(generator.generate()));
        }

        return lottos;
    }
}
