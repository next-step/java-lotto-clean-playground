package domain.generator;

import domain.Lotto;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedLottoGenerator implements LottoGenerator {
    private final List<Lotto> fixedLottos;
    private final AtomicInteger index = new AtomicInteger(0);

    public FixedLottoGenerator(List<Lotto> predefinedLottos) {
        this.fixedLottos = predefinedLottos;
    }

    @Override
    public Lotto generate() {
        int currentIndex = index.getAndIncrement();
        return fixedLottos.get(currentIndex);
    }
}
