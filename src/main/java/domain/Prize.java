package domain;

public enum Prize {
    _4TH_PRIZE( 3, 5_000),
    _3TH_PRIZE( 4, 50_000),
    _2ND_PRIZE( 5, 1_500_000),
    BONUS_PRIZE( 5, 30_000_000),
    _1ST_PRIZE( 6, 2_000_000_000);


    private int Quantity;
    private final int prize;
    private final int correctCount;

    Prize(final int correctCount, final int prize) {
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
