package strategy;

import domain.LottoNumber;
import java.util.List;

public interface LottoNumberGenerator {

    List<LottoNumber> generate();
}
