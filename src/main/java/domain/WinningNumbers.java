package domain;

public enum WinningNumbers {
    FIFTH(5000),
    FOURTH(50000),
    THIRD(1500000),
    SECOND(30000000),
    FIRST(2000000000);

    private final int amount;

    WinningNumbers(int amount) {
        this.amount = amount;
    }

    public int amount() {
        return amount;
    }

    public static int findByIndex(int index) {
        if (index >= 0 && index < values().length) {
            return values()[index].amount();
        }

        throw new IllegalArgumentException();
    }

}
