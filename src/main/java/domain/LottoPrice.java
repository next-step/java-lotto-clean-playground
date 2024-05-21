package domain;

public record LottoPrice(int price) {
    public static final int MIN_PRICE = 0;
    public static final int PRICE_UNIT = 1_000;

    public static final String PRICE_NOT_NUMERIC_MESSAGE = "로또 금액은 숫자여야 합니다.";
    public static final String PRICE_LESS_THAN_MIN_MESSAGE = "로또 금액은 0원 보다 작을 수 없습니다.";
    public static final String PRICE_NOT_IN_UNIT_MESSAGE = "로또 금액은 1000원 단위여야 합니다.";

    public LottoPrice {
        validate(price);
    }

    public static LottoPrice valueOf(String price) {
        try {
            return new LottoPrice(Integer.parseInt(price));
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(PRICE_NOT_NUMERIC_MESSAGE);
        }
    }

    private void validate(int price) {
        validateRange(price);
        validateUnit(price);
    }

    private void validateRange(int price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException(PRICE_LESS_THAN_MIN_MESSAGE);
        }
    }

    private void validateUnit(int price) {
        if (price % PRICE_UNIT != 0) {
            throw new IllegalArgumentException(PRICE_NOT_IN_UNIT_MESSAGE);
        }
    }

    public int divideByUnit() {
        return price() / PRICE_UNIT;
    }
}