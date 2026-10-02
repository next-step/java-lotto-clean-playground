package domain;

// 구입 금액 원시값 포장
public class PurchasePrice {
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    public PurchasePrice(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        validateMinimumAmount(amount);
        validateUnit(amount);
    }

    private void validateMinimumAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(
                    "로또 최소 구입 금액은 " + LOTTO_PRICE + "원입니다."
            );
        }
    }

    private void validateUnit(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    "구입 금액은 " + LOTTO_PRICE + "원 단위여야 합니다."
            );
        }
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public double calculateRateOfReturn(long revenue) {
        return (double) revenue / amount;
    }
}
