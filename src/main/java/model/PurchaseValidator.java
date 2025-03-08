package model;

public class PurchaseValidator {

    public static int validateLottoInputName(String input) {
        int amount = parsePurchaseAmount(input);
        validatePurchaseAmount(amount);
        return amount;
    }

    private static int parsePurchaseAmount(String input) {
        try {
            return Integer.parseInt(String.valueOf(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구매 금액은 숫자여야 합니다.");
        }
    }

    private static void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("구매 금액은 양수여야 합니다.");
        }
        if (purchaseAmount % LottoConstants.LOTTO_TICKET_PRICE.getValue() != 0) {
            throw new IllegalArgumentException("구매 금액은 1000원 단위로 입력되어야 합니다.");
        }
    }

}
