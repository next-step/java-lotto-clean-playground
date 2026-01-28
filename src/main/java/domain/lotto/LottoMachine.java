package domain.lotto;

import domain.generator.RandomLottoGenerator;
import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {

    private final RandomLottoGenerator generator;

    public LottoMachine(RandomLottoGenerator generator) {
        this.generator = generator;
    }

    public Lottos issue(int count) {
        List<Lotto> issuedLottos = IntStream.range(0, count)
            .mapToObj(i -> Lotto.from(generator.generateLottoNumbers()))
                .toList();
        return new Lottos(issuedLottos);
    }

}
