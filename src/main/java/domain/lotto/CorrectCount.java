package domain.lotto;

public class CorrectCount {
    private final int correctCount;

    private final boolean hasBonusNumber;

    public CorrectCount(int correctCount, boolean hasBonusNumber) {
        this.correctCount = correctCount;
        this.hasBonusNumber = hasBonusNumber;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public boolean hasBonusNumber() {
        return hasBonusNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CorrectCount)) {
            throw new IllegalArgumentException("Argument should be CorrectCount");
        }

        return correctCount == ((CorrectCount)obj).correctCount;
    }
}
