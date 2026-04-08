package domain;

public class PurchaseCount {
    private final int manual;
    private final int auto;

    public PurchaseCount(int total, int manual) {
        validate(total, manual);
        this.manual = manual;
        this.auto = total - manual;
    }

    private void validate(int total, int manual) {
        if (manual < 0 || manual > total) {
            throw new IllegalArgumentException("수동 구매 개수는 0개 이상, 총 구매 가능 개수 이하여야 합니다.");
        }
    }

    public int getManual() {
        return manual;
    }

    public int getAuto() {
        return auto;
    }
}