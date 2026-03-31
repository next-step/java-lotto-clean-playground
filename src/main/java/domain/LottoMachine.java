package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private final LottoNumberGenerator generator;

    public LottoMachine(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public List<Lotto> issue(int trialCount) {
        List<Lotto> generatedLottos = new ArrayList<>();
        for (int i = 0; i < trialCount; i++) {
            generatedLottos.add(new Lotto(generator.generate()));
        }
        return generatedLottos;
    }
}