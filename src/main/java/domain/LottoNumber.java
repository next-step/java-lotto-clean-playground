package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoNumber {
    private static final int LOTTO_MINIMUM_NUMBER = 1;
    private static final int LOTTO_MAXIMUM_NUMBER = 45;
    private static final String OUT_OF_LOTTO_NUMBER = "로또 번호는 1부터 45 사이어야 합니다.";
    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

    private final int number;

    public LottoNumber(int number) {
        this.number = number;
    }

    static {
        for (int i=LOTTO_MINIMUM_NUMBER; i<=LOTTO_MAXIMUM_NUMBER; i++) {
            CACHE.put(i, new LottoNumber(i));
        }
    }

    public static LottoNumber of(int number) {
        if (!CACHE.containsKey(number)) {
            throw new IllegalArgumentException(OUT_OF_LOTTO_NUMBER);
        }
        return CACHE.get(number);
    }

    public int getLottoNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
