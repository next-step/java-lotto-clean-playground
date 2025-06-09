package domain.store;

import domain.lotto.Lotto;
import domain.lotto.Lottos;
import java.util.List;
import strategy.LottoNumberGenerator;
import util.LottoParser;

public final class LottoMachine {

    private final LottoNumberGenerator generator;

    public LottoMachine(final LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public Lottos generateAuto(final int count) {
        return Lottos.generate(count, generator);
    }

    public Lottos generateManual(final List<String> manualNumbers) {
        List<Lotto> lottos = manualNumbers.stream()
                .map(LottoParser::parseNumbers)
                .map(Lotto::new)
                .toList();
        return new Lottos(lottos);
    }
}
