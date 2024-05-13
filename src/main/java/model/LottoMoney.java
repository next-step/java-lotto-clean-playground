package model;

public class LottoMoney {
    private final int amount;

    public LottoMoney(String inputAmount) {
        try {
            int amount = Integer.parseInt(inputAmount);
            validateAmount(amount);
            this.amount = amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Constant.PRICE_VALUE_EXCEPTION, e);
        }
    }

    private void validateAmount(int amount) {
        if (amount % Constant.LOTTO_PRICE != 0 || amount < Constant.LOTTO_PRICE) {
            throw new IllegalArgumentException(Constant.LOTTO_PRICE_EXCEPTION);
        }
    }

    public int getAmount() {
        return amount;
    }
}
