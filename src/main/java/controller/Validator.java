package controller;

import constant.ErrorMessage;
import domain.Lotto;
import domain.LottoNumber;

import java.util.Arrays;
import java.util.List;

public class Validator {
    private static final int MIN_LOTTO_COUNT = 1;
    private static final int LOTTO_PRICE = 1000;

    public List<LottoNumber> validateLastWinningsInput(String input) {
        List<String> strings = Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
        List<LottoNumber> winnings = validateWinningsInputIntegrity(strings);
        new Lotto(winnings);

        return winnings;
    }

    public Integer validatePriceInput(String input) {
        int purchasePrice = validatePriceInputIntegrity(input);
        int purchaseAmount = validateUnit(purchasePrice);
        validateLottoCount(purchaseAmount);
        return purchaseAmount;
    }

    private List<LottoNumber> validateWinningsInputIntegrity(List<String> strings) {
        try {
            return strings.stream()
                    .map(Integer::parseInt)
                    .map(LottoNumber::new)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }
    }

    private int validateUnit(int purchasePrice) {
        int purchaseAmount = purchasePrice % LOTTO_PRICE;
        if (purchaseAmount > 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_UNIT.getMessage());
        }
        return purchasePrice / LOTTO_PRICE;
    }

    private void validateLottoCount(int purchaseAmount) {
        if (purchaseAmount < MIN_LOTTO_COUNT) {
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.INVALID_PURCHASE_AMOUNT.getMessage())
            );
        }
    }

    private int validatePriceInputIntegrity(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }
    }
}
