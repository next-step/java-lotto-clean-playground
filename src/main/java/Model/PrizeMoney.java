package Model;

public enum PrizeMoney {
    THREE(5000),
    FOUR(50000),
    FIVE(1500000),
    SIX(2000000000),
    BONUS(30000000);

    private final int money;

    private PrizeMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
