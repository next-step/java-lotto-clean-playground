package domain;

public class PurchasePrice {

    private final int purchasePrice;

    public PurchasePrice(int purchasePrice){
        verifyPurchasePrice(purchasePrice);
        this.purchasePrice = purchasePrice;
    }

    private void verifyPurchasePrice(int purchasePrice) {

        try {
            if (purchasePrice <= 0) {
                throw new IllegalArgumentException("양수의 가격을 입력해주세요.");
            }

            if (purchasePrice % 1000 != 0) {
                throw new IllegalArgumentException("1000의 배수로 입력해주세요.");
            }
        }

        catch (NumberFormatException e){
                throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }

    public int getLottoNumberCount() {
        return purchasePrice / 1000;
    }

    public float calculateProfit(long price) {
        return (float) price / purchasePrice;
    }
}
