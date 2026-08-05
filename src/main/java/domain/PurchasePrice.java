package domain;

public class PurchasePrice {

    private static final int LOTTO_PRICE = 1000;

    private final int purchasePrice;
    private final int autoLottoCount;
    private final int manualLottoCount;

    public PurchasePrice(int purchasePrice, int manualLottoCount) {
        verifyPurchasePrice(purchasePrice);
        verifyManualCount(purchasePrice, manualLottoCount);

        this.purchasePrice = purchasePrice;
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = purchasePrice / LOTTO_PRICE - manualLottoCount;
    }

    private void verifyPurchasePrice(int purchasePrice) {
        if (purchasePrice <= 0) {
            throw new IllegalArgumentException("양수의 가격을 입력해주세요.");
        }

        if (purchasePrice % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("1000의 배수로 입력해주세요.");
        }
    }

    private void verifyManualCount(int purchasePrice, int manualLottoCount) {
        if (manualLottoCount < 0) {
            throw new IllegalArgumentException("수동 로또 수는 0 이상이어야 합니다.");
        }

        int totalLottoCount = purchasePrice / LOTTO_PRICE;

        if (manualLottoCount > totalLottoCount) {
            throw new IllegalArgumentException("구매 가능한 로또 수를 초과했습니다.");
        }
    }

    public int getManualLottoCount() { // 수동 로또 수 반환
        return manualLottoCount;
    }

    public int getAutoLottoCount() { // 자동 로또 수 반환
        return autoLottoCount;
    }

    public float calculateProfit(long prize) {
        return (float) prize / purchasePrice;
    }
}
