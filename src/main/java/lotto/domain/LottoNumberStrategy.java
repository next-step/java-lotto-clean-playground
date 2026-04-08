package lotto.domain;

import java.util.List;

@FunctionalInterface
public interface LottoNumberStrategy {
    List<Integer> generate();
}
