package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static final Map<Integer, LottoNumber> CACHE;
    private final int number;

    static {
        Map<Integer, LottoNumber> temp = new HashMap<>();
        for (int i = LOTTO_MIN; i <= LOTTO_MAX; i++) {
            temp.put(i, new LottoNumber(i));
        }
        CACHE = Collections.unmodifiableMap(temp);
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        validate(number);
        return CACHE.get(number);
    }

    private static void validate(int number) {
        if (number < LOTTO_MIN || number > LOTTO_MAX) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이여야 합니다.");
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
        if (!(o instanceof LottoNumber)) {
            return false;
        }
        LottoNumber compareNumber = (LottoNumber) o;
        return number == compareNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    public int getNumber() {
        return number;
    }
}
