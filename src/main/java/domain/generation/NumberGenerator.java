package domain.generation;

import domain.lotto.LottoNumber;

import java.util.List;

public interface NumberGenerator {
    List<LottoNumber> generate();
}
