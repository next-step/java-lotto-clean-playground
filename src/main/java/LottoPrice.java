public record LottoPrice(int price) {

    public static final int MIN_PRICE = 0;
    public static final int PRICE_UNIT = 1_000;

    public LottoPrice {
        validate(price);
    }

    private static void validate(int price) {
        validateRange(price);
        validateUnit(price);
    }

    private static void validateRange(int price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException("로또 금액은 0원 보다 작을 수 없습니다.");
        }
    }

    private static void validateUnit(int price) {
        if (price % PRICE_UNIT != 0) {
            throw new IllegalArgumentException("로또 금액은 1000원 단위여야 합니다.");
        }
    }

    public static LottoPrice valueOf(String price) {
        try {
            return new LottoPrice(Integer.parseInt(price));
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("로또 금액은 숫자여야 합니다.");
        }
    }

    public int divideByUnit() {
        return price() / PRICE_UNIT;
    }
}
