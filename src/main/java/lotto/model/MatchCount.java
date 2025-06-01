package lotto.model;

public class MatchCount {

    private final int count;
    private final boolean matchBonus;

    public MatchCount(int count, boolean matchBonus) {
        this.count = count;
        this.matchBonus = matchBonus;
    }

    public int getCount() {
        return count;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }
}
