package domain;

public record BudgetInfo(int budget) {
    public static final int LOTTO_UNIT = 1_000;
    public static final int MIN_PRICE = 0;

    public static BudgetInfo valueOf(String budget) {
        try {
            return new BudgetInfo(Integer.parseInt(budget));
        } catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException("로또 구매 입력은 숫자로 해야합니다.");
        }
    }

    public BudgetInfo {
        validateRange(budget);
        validateUnit(budget);
    }

    private void validateRange(int budget) {
        if (budget < MIN_PRICE) {
            throw new IllegalArgumentException("로또 구매 금액은 0원 이상이어야 합니다.");
        }
    }

    private void validateUnit(int budget) {
        if ((budget % LOTTO_UNIT) != 0) {
            throw new IllegalArgumentException("로또 구매 금액은 1000원 단위여야 합니다.");
        }
    }

}
