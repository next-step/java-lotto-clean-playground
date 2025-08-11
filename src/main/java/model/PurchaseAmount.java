package model;

import exception.CustomException;
import exception.ErrorMessage;
import util.Parser;

public class PurchaseAmount {

    private final int amount;

    private static final int LOTTO_PRICE = 1000;

    public PurchaseAmount(String inputAmount) {
        this.amount = Parser.parseInt(inputAmount);
        validate(amount);
    }

    public int getAmount() {
        return amount;
    }

    private void validate(int amount) {
        if (amount <= 0 || amount % LOTTO_PRICE != 0) {
            throw new CustomException(ErrorMessage.INVALID_AMOUNT_VALUE);
        }
    }

    public int getTotalLottoCount() {
        return amount / LOTTO_PRICE;
    }
}
