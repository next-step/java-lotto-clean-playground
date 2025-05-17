package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {
    private static final int LOTTO_PRICE = 1_000;
    private LottoNumbersGenerator lottoNumbersGenerator;

    public LottoGenerator(LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbersGenerator = lottoNumbersGenerator;
    }

    public Lottos generate(int purchasePrice,int manualLottoCount) {
        final int lottoCount = purchasePrice / LOTTO_PRICE - manualLottoCount;
        final List<Lotto> lottos = new ArrayList<>(lottoCount);

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(lottoNumbersGenerator.generate()));
        }

        return new Lottos(lottos);
    }
}
