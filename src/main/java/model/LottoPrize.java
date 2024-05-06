package model;

public enum LottoPrize {
    FIFTH(Constant.THREE_COUNT, Constant.FIFTH_PRICE, false),
    FOURTH(Constant.FOUR_COUNT, Constant.FOURTH_PRICE, false),
    THIRD(Constant.FIVE_COUNT, Constant.THIRD_PRICE, false),
    SECOND(Constant.FIVE_COUNT, Constant.SECOND_PRICE, true),
    FIRST(Constant.SIX_COUNT, Constant.FIRST_PRICE, false);

    private final int matchCount;
    private final int prizeAmount;
    private final boolean hasBonus;

    LottoPrize(int matchCount, int prizeAmount, boolean hasBonus) {
        this.matchCount = matchCount;
        this.prizeAmount = prizeAmount;
        this.hasBonus = hasBonus;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }

    public boolean hasBonus() {
        return hasBonus;
    }

}
