package lotto;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1000;

    private final int value;

    public PurchaseAmount(int value) {
        this.value = value;
    }

    public int calculateLottoCount() {
        return value / LOTTO_PRICE;
    }

    public int calculateAutomaticLottoCount(int manualLottoCount) {
        validateManualLottoCount(manualLottoCount);
        return calculateLottoCount() - manualLottoCount;
    }

    private void validateManualLottoCount(int manualLottoCount) {
        if (manualLottoCount < 0 || manualLottoCount > calculateLottoCount()) {
            throw new IllegalArgumentException("수동 구매 수는 전체 구매 수를 넘을 수 없습니다.");
        }
    }

    public ProfitRate calculateProfitRate(PrizeMoney prizeMoney) {
        return new ProfitRate((double) prizeMoney.amount() / value);
    }
}
