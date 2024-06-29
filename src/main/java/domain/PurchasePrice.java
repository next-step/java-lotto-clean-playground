package domain;

import util.Errors;

public record PurchasePrice(int price) {

    public PurchasePrice {
        validatePriceIsNotNegative(price);
    }

    private void validatePriceIsNotNegative(int price) {
        if (price < 0) {
            throw new IllegalArgumentException(Errors.INPUT_PRICE_IS_NEGATIVE);
        }
    }

}
