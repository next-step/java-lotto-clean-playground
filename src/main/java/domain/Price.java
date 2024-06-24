package domain;

import util.Errors;

public record Price(int price) {

    public Price {
        validatePriceIsNotNegative(price);
    }

    private void validatePriceIsNotNegative(int price) {
        if (price < 0) {
            throw new IllegalArgumentException(Errors.INPUT_PRICE_IS_NEGATIVE);
        }
    }

}
