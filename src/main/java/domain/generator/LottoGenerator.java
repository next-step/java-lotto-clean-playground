package domain.generator;

import domain.Lotto;

@FunctionalInterface
public interface LottoGenerator {
    Lotto generate();
}
