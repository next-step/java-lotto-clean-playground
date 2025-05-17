package domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final Map<Integer, LottoNumber> CACHE = new ConcurrentHashMap<>();
    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(int number) {
        validate(number);
        return CACHE.computeIfAbsent(number, LottoNumber::new);
    }

    private static void validate(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("유효한 로또 숫자가 아닙니다.");
        }
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.number, other.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }

    public int getNumber() {
        return number;
    }
}
