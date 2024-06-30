package domain;

public enum LottoRank {

    FIRST(6, 2000000000),
    BONUS(5,30000000, true),
    SECOND(5, 1500000),
    THIRD(4, 50000),
    FOURTH(3, 5000),
    NONE(0,0);


    private final int count;
    private final int prize;
    private final boolean bonusMatch;

    LottoRank(int count, int prize) {
        this.count = count;
        this.prize = prize;
        bonusMatch = false;
    }

    LottoRank(int count, int prize, boolean bonusMatch) {
        this.count = count;
        this.prize = prize;
        this.bonusMatch = bonusMatch;
    }

    public int getCount() {
        return count;
    }

    public int getPrize() {
        return prize;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }
}
