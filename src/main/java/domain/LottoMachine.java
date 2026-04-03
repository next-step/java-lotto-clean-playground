package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {
    private final LottoNumberGenerator generator;

    public LottoMachine(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public List<Lotto> issue(int trialCount) {
        return IntStream.range(0, trialCount)
                .mapToObj(i -> new Lotto(generator.generate()))
                .collect(Collectors.toList());
    }
}
