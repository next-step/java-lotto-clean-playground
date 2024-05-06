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
            throw new IllegalArgumentException("로또 금액은 0이상 이어야 한다.");
        }
    }

    private void validateUnit(int price) {
        if (price % PRICE_UNIT != 0) {
            throw new IllegalArgumentException("로또 금액은 1000원 단위여야 한다.");
        }
    }

    public int getPrice() {
        return price;
    }
}
