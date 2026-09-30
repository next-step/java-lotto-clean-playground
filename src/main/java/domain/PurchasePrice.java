package domain;

// 구입 금액 원시값 포장
public class PurchasePrice {
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    public PurchasePrice(String input) {
        this.amount = parseToInt(input.trim());
        validate(amount);
    }

    private int parseToInt(String input) {
        validateNotBlank(input);

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "구입 금액은 숫자로 입력해야 합니다."
            );
        }
    }

    private void validateNotBlank(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(
                    "구입 금액을 입력해야 합니다."
            );
        }
    }

    private void validate(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(
                    "로또 최소 구입 금액은 " + LOTTO_PRICE + "원입니다."
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
