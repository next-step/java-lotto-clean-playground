package domain.generator;

import domain.Lotto;

import java.util.List;

public interface LottoGenerator {

    List<Lotto> generateLottos(int count);
}
