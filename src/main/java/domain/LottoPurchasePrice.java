package domain;

import util.Errors;

public record LottoPurchasePrice(int price) {

    public LottoPurchasePrice {
        validatePriceIsNotNegative(price);
    }

    private void validatePriceIsNotNegative(int price) {
        if (price < 0) {
            throw new IllegalArgumentException(Errors.INPUT_PRICE_IS_NEGATIVE);
        }
    }

    public int getLottoCount() {
        if (Lotto.PRICE == 0) {
            return this.price;
        }
        return this.price / Lotto.PRICE;
    }
}
