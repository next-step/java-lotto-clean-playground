package domain.generator;

import domain.Lotto;
import domain.LottoNumber;

import java.util.List;

public interface LottoGenerator {

    Lotto generate();
    List<Lotto> generateLottos(int count);
}
