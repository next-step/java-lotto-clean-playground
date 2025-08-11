package model;

public class AutoCount {

    private final int autoCount;

    public AutoCount(int autoCount) {
        this.autoCount = autoCount;
    }

    public int getAutoCount() {
        return autoCount;
    }

    @Override
    public String toString() {
        return String.valueOf(autoCount);
    }
}
