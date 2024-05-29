package org.duckstudy.utils;

import java.util.stream.IntStream;
import org.duckstudy.model.lotto.Lotto;
import org.duckstudy.model.lotto.LottoNumber;

public class LottoTestUtils {

    public static Lotto createLotto(int... values) {
        return new Lotto(IntStream.of(values)
                .mapToObj(LottoNumber::valueOf)
                .toList());
    }
}
