package model;

import java.util.Arrays;
import java.util.List;

public class LottoFixture {
    public static final List<LottoNumber> DEFAULT_WINNING_NUMBERS = Arrays.asList(
            new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
            new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
    );
    public static final Lotto LOTTO_MATCH_6 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
    public static final Lotto LOTTO_MATCH_5 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7));
    public static final Lotto LOTTO_MATCH_4 = new Lotto(Arrays.asList(1, 2, 3, 4, 7, 8));
    public static final Lotto LOTTO_MATCH_3 = new Lotto(Arrays.asList(1, 2, 3, 7, 8, 9));
    public static final Lotto LOTTO_MATCH_0 = new Lotto(Arrays.asList(10, 11, 12, 13, 14, 15));
}
