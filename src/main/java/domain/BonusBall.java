package domain;

public class BonusBall {
    private final static int MINIMUM_LOTTO_NUMBER = 1;
    private final static int MAXIMUM_LOTTO_NUMBER = 45;

    private final int bonusBall;

    public BonusBall(int bonusBall) {
        if (bonusBall < MINIMUM_LOTTO_NUMBER || bonusBall > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException("보너스 볼은 1~45 사이의 숫자입니다.");
        }
        this.bonusBall = bonusBall;
    }

    public static BonusBall of(int bonusBall) {
        return new BonusBall(bonusBall);
    }

    public int getBonusBall() {
        return bonusBall;
    }
}
