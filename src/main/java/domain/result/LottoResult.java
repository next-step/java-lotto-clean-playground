package domain.result;

public class LottoResult {
    private final MatchCount matchCount;
    private final boolean bonusBallMatched;

    private LottoResult(MatchCount matchCount, boolean bonusBallMatched) {
        this.matchCount = matchCount;
        this.bonusBallMatched = bonusBallMatched;
    }

    public static LottoResult of(MatchCount matchCount, boolean bonusBallMatched) {
        return new LottoResult(matchCount, bonusBallMatched);
    }

    public boolean matches(int matchCount, boolean bonusBallMatched) {
        return this.matchCount.isSame(matchCount) && this.bonusBallMatched == bonusBallMatched;
    }

    public boolean hasMatchCount(int matchCount) {
        return this.matchCount.isSame(matchCount);
    }

    public boolean hasMatchedBonusBall() {
        return bonusBallMatched;
    }

    public boolean hasUnmatchedBonusBall() {
        return !bonusBallMatched;
    }
}
