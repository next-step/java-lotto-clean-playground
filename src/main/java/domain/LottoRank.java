package domain;

public class LottoRank {

    private final int count;
    private final int prize;

    public LottoRank(int count, int prize) {
        this.count = count;
        this.prize = prize;
    }

    public int getCount() {
        return count;
    }

    public int getPrize() {
        return prize;
    }
}
