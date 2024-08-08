package domain;

import util.Errors;

public class LottoPurchasePrice {

    private final int price;
    private final int lottoCount;

    public LottoPurchasePrice(int price) {
        validatePriceIsNotNegative(price);
        this.price = price;
        this.lottoCount = calculateLottoCount();
    }

    private void validatePriceIsNotNegative(int price) {
        if (price < 0) {
            throw new IllegalArgumentException(Errors.INPUT_PRICE_IS_NEGATIVE);
        }
    }

    private int calculateLottoCount() {
        if (Lotto.PRICE == 0) {
            return this.price;
        }
        return this.price / Lotto.PRICE;
    }

    public int getLottoCount() {
        return this.lottoCount;
    }

    public int getPrice() {
        return this.price;
    }
}
