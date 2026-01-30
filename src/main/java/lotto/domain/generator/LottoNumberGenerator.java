package lotto.domain.generator;

import java.util.List;
import lotto.domain.model.LottoNumber;

@FunctionalInterface
public interface LottoNumberGenerator {
    List<LottoNumber> generateLottoNumbers();

}
