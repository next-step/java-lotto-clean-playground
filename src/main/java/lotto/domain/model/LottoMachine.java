package lotto.domain.model;

import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.generator.RandomLottoGenerator;
import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {

    private final LottoNumberGenerator generator;

    public LottoMachine(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public Lottos issue(int count) {
        List<Lotto> issuedLottos = IntStream.range(0, count)
            .mapToObj(i -> Lotto.from(generator.generateLottoNumbers()))
                .toList();
        return new Lottos(issuedLottos);
    }

}
