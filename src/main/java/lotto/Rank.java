package lotto;

public enum Rank {
    _1ST_PLACE(6, false, Constant.FIRST_REWARD),
    _2ND_PLACE(5, true, Constant.SECOND_REWARD),
    _3RD_PLACE(5, false, Constant.THIRD_REWARD),
    _4TH_PLACE(4, false, Constant.FOURTH_REWARD),
    _5TH_PLACE(3, false, Constant.FIFTH_REWARD),
    _NO_PLACE(0, false, Constant.NO_REWARD);

    private final int match;
    private final boolean bonus;
    private final int reward;

    Rank(int match, boolean bonus, int reward) {
        this.match = match;
        this.bonus = bonus;
        this.reward = reward;
    }

    public static Rank of(int match, boolean bonus) {
        for (Rank rank : Rank.values()) {
            if (rank.match == match && rank.bonus == bonus) {
                return rank;
            }
        }

        return _NO_PLACE;
    }

    public int getMatch() {
        return match;
    }

    public boolean getBonus() {
        return bonus;
    }

    public int getReward() {
        return reward;
    }
}
