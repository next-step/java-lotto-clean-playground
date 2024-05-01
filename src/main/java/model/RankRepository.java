package model;

public class RankRepository {
    private int rank1;
    private int rank2;
    private int rank3;
    private int rank4;

    public double rateOfReturn() {
        int total = rank1*2000000000 + rank2*1500000 + rank3*50000 + rank4*5000;
        return
    }

    public int getRank1() {
        return rank1;
    }

    public void setRank1(int rank1) {
        this.rank1 = rank1;
    }

    public int getRank2() {
        return rank2;
    }

    public void setRank2(int rank2) {
        this.rank2 = rank2;
    }

    public int getRank3() {
        return rank3;
    }

    public void setRank3(int rank3) {
        this.rank3 = rank3;
    }

    public int getRank4() {
        return rank4;
    }

    public void setRank4(int rank4) {
        this.rank4 = rank4;
    }
}
