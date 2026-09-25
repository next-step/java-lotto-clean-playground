package domain;

// 구입 금액 원시값 포장
public class PurchasePrice {
    private final int amount;

    public PurchasePrice(String input) {
        this.amount = parseToInt(input);
        validate(amount);
    }

    public int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("구입 금액은 숫자로 입력되어야 합니다.");
        }
    }

    public void validate(int amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException("로또 최소 구매 금액은 1000원입니다.");
        }
    }

    public int calculateLottoCount() {
        return amount / 1000;
    }

    public int getAmount() {
        return amount;
    }
}
