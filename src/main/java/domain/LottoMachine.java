package domain;

import domain.numberGenerator.NumberGenerator;
import enums.LottoType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {

    private final NumberGenerator numberGenerator;

    public LottoMachine(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> generateLottos(LottoCount lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount.getLottoCount(); i++) {
            Lotto lotto = createSingleLotto();
            lottos.add(lotto);
        }
        return lottos;
    }

    private Lotto createSingleLotto() {
        List<Integer> numbers = numberGenerator.generateNumbers();
        Collections.sort(numbers);
        return Lotto.from(numbers, LottoType.AUTO);
    }
}
