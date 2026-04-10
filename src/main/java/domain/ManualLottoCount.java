package domain;

public class ManualLottoCount {
    private final int count;

    public ManualLottoCount(int count, PurchaseAmount purchaseAmount) {
        validate(count, purchaseAmount);
        this.count = count;
    }

    private void validate(int count, PurchaseAmount purchaseAmount) {
        if (count < 0) {
            throw new IllegalArgumentException("수동 구매 수는 0 이상이어야 합니다.");
        }
        if (count > purchaseAmount.calculateLottoCount()) {
            throw new IllegalArgumentException("수동 로또 수는 총 로또 수를 넘을 수 없습니다.");
        }
    }

    public int count() {
        return count;
    }
}
