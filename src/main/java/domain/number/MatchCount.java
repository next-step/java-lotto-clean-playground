package domain.number;

public class MatchCount {
    private final int value;

    private MatchCount(int value) {
        this.value = value;
    }

    public static MatchCount from(int value) {
        return new MatchCount(value);
    }

    public boolean isSame(int other) {
        return value == other;
    }
}
