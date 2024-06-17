package model;

public class Money {

    private final int value;

    public Money(int value) {
        validateValue(value);
        this.value = value;
    }

    private void validateValue(final int value)  {
        if (value < 0) {
            throw new IllegalArgumentException("구입금액은 음수가 될 수 없습니다.");
        }

        if (value % 1000 != 0) {
            throw new IllegalArgumentException("구입금액은 1000단위이어야 합니다.");
        }
    }
}
