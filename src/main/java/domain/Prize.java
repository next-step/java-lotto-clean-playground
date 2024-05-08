package domain;

public enum Prize {
    _4TH_PRIZE(false, 3, 5_000),
    _3TH_PRIZE(false, 4, 50_000),
    _2ND_PRIZE(false, 5, 1_500_000),
    BONUS_PRIZE(true, 5, 30_000_000),
    _1ST_PRIZE(false, 6, 2_000_000_000);


    private int Quantity;
    private final boolean containBonus;
    private final int prize;
    private final int correctCount;

    Prize(final boolean containBonus, final int correctCount, final int prize) {
        this.containBonus = containBonus;
        this.correctCount = correctCount;
        this.prize = prize;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getPrize() {
        return prize;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void addQuantity() {
        Quantity++;
    }

    public static int getTotalPrize() {
        int totalPrize = 0;
        for (Prize value : Prize.values()) {
            totalPrize += value.getPrize() * value.getQuantity();
        }

        return totalPrize;
    }
}
