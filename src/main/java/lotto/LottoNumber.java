package lotto;

public class LottoNumber {
    private final int value;

    public LottoNumber(int value) {
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
