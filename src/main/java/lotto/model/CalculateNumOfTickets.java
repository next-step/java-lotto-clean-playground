package lotto.model;

public class CalculateNumOfTickets {
    private static final int TICKET_PRICE = 1000;
    private static final int MINIMUM_PURCHASE_PRICE=0;

    private int numOfTickets=0;
    private int purchasePrice=0;

    public void settingNumOfTickets(int purchasePrice) {
        validatePriceGreaterThanMinimumPurchasePrice(purchasePrice);
        this.purchasePrice=purchasePrice;
        this.numOfTickets = purchasePrice/TICKET_PRICE;
    }

    private void validatePriceGreaterThanMinimumPurchasePrice(int purchasePrice){
        if (purchasePrice <= MINIMUM_PURCHASE_PRICE) {
            throw new IllegalArgumentException("구매 금액은 양수여야 합니다.");
        }
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }
}
