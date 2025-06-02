package lotto.model;

public class BonusBall {

    private final int value;

    public BonusBall(int value) {
        if (value < 1 || value > 45) {
            throw new IllegalArgumentException("보너스 번호는 1~45 사이여야 합니다.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
