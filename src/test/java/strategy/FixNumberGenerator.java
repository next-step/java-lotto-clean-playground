package strategy;

import domain.lotto.LottoNumber;
import java.util.List;

public class FixNumberGenerator implements LottoNumberGenerator {

    @Override
    public List<LottoNumber> generate() {
        return List.of(LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
        );
    }
}
