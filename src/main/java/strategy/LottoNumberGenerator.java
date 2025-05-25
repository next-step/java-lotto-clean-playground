package strategy;

import domain.lotto.LottoNumber;
import java.util.List;

public interface LottoNumberGenerator {

    List<LottoNumber> generate();
}
