package domain.generator;

import domain.Lotto;

import java.util.List;

public class FixedLottoGenerator implements LottoGenerator {
    private final List<Lotto> fixedLottos;
    private int index = 0;

    public FixedLottoGenerator(List<Lotto> predefinedLottos) {
        this.fixedLottos = predefinedLottos;
    }

    @Override
    public Lotto generate() {
        return fixedLottos.get(index++);
    }
}
