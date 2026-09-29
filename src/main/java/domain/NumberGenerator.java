package domain;

import domain.purchase.LottoNumber;
import java.util.List;

public interface NumberGenerator {
    List<LottoNumber> generate();
}
