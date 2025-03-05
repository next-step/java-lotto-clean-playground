package model;

public enum LottoPrize {
    FIFTH(5_000, 3, false),
    FOURTH(50_000, 4, false),
    THIRD(1_500_000, 5, false),
    SECOND(30_000_000, 5, true),
    FIRST(2_000_000_000, 6, false);

    private final int money;
    private final int matchNumberCount;
    private final boolean hasBonusNumber;

    LottoPrize(int money, int matchNumberCount, boolean hasBonusNumber) {
        this.money = money;
        this.matchNumberCount = matchNumberCount;
        this.hasBonusNumber = hasBonusNumber;
    }

    public static LottoPrize getPrizeByMatchResult(int matchNumberCount, boolean hasBonusNumber) {
        for (LottoPrize lottoPrize: values()) {
            if (matchNumberCount != lottoPrize.matchNumberCount) {
                continue;
            }

            if (matchNumberCount == 5 && hasBonusNumber != lottoPrize.hasBonusNumber) {
                continue;
            }

            return lottoPrize;
        }

        return null;
    }

    public int getMoney() {
        return money;
    }

    public int getMatchNumberCount() {
        return matchNumberCount;
    }
}
