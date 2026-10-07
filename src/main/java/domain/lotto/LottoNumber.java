package domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int value;

    public LottoNumber(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < MIN_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException(
                    "로또의 범위는 " + MIN_NUMBER + "~" + MAX_NUMBER + " 사이여야 합니다."
            );
        }
    }

    public static List<LottoNumber> allNumbers() {
        List<LottoNumber> numbers = new ArrayList<>();

        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            numbers.add(new LottoNumber(i));
        }

        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber)) {
            return false;
        }
        LottoNumber other = (LottoNumber) o;
        return value == other.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
