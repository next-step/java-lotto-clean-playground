package domain;

public enum WinningPrize {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    BONUS(7, 30000000),
    SIX(6, 2000000000);

    private final int goal;
    private final int prize;

    WinningPrize(int goal, int prize) {
        this.goal = goal;
        this.prize = prize;
    }

    public int getGoal() {
        return goal;
    }

    public int getPrize() {
        return prize;
    }
}
