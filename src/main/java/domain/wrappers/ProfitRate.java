package domain.wrappers;

public class ProfitRate {
    private final double value;

    public ProfitRate(int expense, int income) {
        this.value = (double) income / expense;
    }

    public double getValue() {
        return value;
    }
}
