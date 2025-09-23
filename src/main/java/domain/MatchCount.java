package domain;

import java.util.List;

public class MatchCount {
    private int match3Count;
    private int match4Count;
    private int match5Count;
    private int match6Count;

    public MatchCount() {
        this.match3Count = 0;
        this.match4Count = 0;
        this.match5Count = 0;
        this.match6Count = 0;
    }

    public void addMatch3Count(int count) {
        this.match3Count += count;
    }
    public void addMatch4Count(int count) {
        this.match4Count += count;
    }
    public void addMatch5Count(int count) {
        this.match5Count += count;
    }
    public void addMatch6Count(int count) {
        this.match6Count += count;
    }

    public int getMatch3Count() {
        return match3Count;
    }
    public int getMatch4Count() {
        return match4Count;
    }
    public int getMatch5Count() {
        return match5Count;
    }
    public int getMatch6Count() {
        return match6Count;
    }

    public static MatchCount countAllMatches(List<Lotto> tickets, Lotto answer) {
        MatchCount matchCount = new MatchCount();
        for (Lotto lotto : tickets) {
            int count = Match.getMatchCount(lotto, answer);
            if (count == 3) matchCount.addMatch3Count(1);
            if (count == 4) matchCount.addMatch4Count(1);
            if (count == 5) matchCount.addMatch5Count(1);
            if (count == 6) matchCount.addMatch6Count(1);
        }
        return matchCount;
    }
}
