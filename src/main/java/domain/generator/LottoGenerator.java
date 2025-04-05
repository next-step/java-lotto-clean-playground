package domain.generator;

import domain.Lotto;

import java.util.List;

public interface LottoGenerator {

    List<Lotto> generateLottoList(int count);
}
