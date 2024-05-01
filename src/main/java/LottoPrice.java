public class LottoPrice {

    public static final int MIN_PRICE = 0;
    public static final int PRICE_UNIT = 1_000;

    private final int price;

    public LottoPrice(int price) {
        validate(price);
        this.price = price;
    }

    private void validate(int price) {
        validateRange(price);
        validateUnit(price);
    }


    private void validateRange(int price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateUnit(int price) {
        if (price % PRICE_UNIT != 0) {
            throw new IllegalArgumentException();
        }
    }
}
