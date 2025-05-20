package lotto;

public class LottoNumber {
    private final int value;
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public LottoNumber(int value) {
        if (value < MINIMUM_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1 ~45 사이의 숫자 입니다.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LottoNumber)) return false;
        return this.value == ((LottoNumber) object).value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
