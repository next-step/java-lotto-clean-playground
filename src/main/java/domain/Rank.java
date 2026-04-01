package domain;

public enum Rank {
    SIX(6,2000000000),
    FIVE(5,1500000),
    FOUR(4,50000),
    THREE(3,5000),
    MISS(0,0);

    private int matchnumbers;
    private int prizemoney;

    Rank (Integer matchnumbers, Integer prizemoney){
        this.matchnumbers = matchnumbers;
        this.prizemoney = prizemoney;
    }

    public Rank valueOf(Integer matchnumbers){
        for (Rank rank : values()) {
            if (rank.matchnumbers == matchnumbers){
                return rank;
            }
        }

        return MISS;
    }

    public int getMatchnumbers() {
        return matchnumbers;
    }

    public int getPrizemoney() {
        return prizemoney;
    }
}

