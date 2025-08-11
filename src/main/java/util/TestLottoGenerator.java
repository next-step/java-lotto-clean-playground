package util;

import model.Lotto;
import model.LottoNumber;

import java.util.List;

public class TestLottoGenerator implements LottoGenerator {

    public TestLottoGenerator() { }

    @Override
    public Lotto generate() {
        return new Lotto(
            List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
            )
        );
    }
}
