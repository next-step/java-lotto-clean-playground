package controller;

import constant.ErrorMessage;
import domain.Lotto;
import domain.LottoNumber;

import java.util.Arrays;
import java.util.List;

public class Validator {
    private static final int MIN_LOTTO_COUNT = 1;
    private static final int LOTTO_PRICE = 1000;

    public LottoNumber validateBonusNumberInput(Lotto winningLotto, String input) {
        try {
            LottoNumber bonusNumber = LottoNumber.valueOf(Integer.parseInt(input));
            validateNoDuplicateBonusNumber(winningLotto, bonusNumber);
            return bonusNumber;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }
    }

    public List<LottoNumber> validateLottoInput(String input) {
        List<String> strings = Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
        List<LottoNumber> winnings = validateLottoIntegrity(strings);
        new Lotto(winnings);

        return winnings;
    }

    public int validatePriceInput(String input) {
        int purchasePrice = validateIntegerInputIntegrity(input);
        int purchaseAmount = validateUnit(purchasePrice);
        validateLottoCount(purchaseAmount);
        return purchaseAmount;
    }

    public int validateManualInput(String input, int purchaseAmount) {
        int manualCount = validateIntegerInputIntegrity(input);
        validateNonNegative(manualCount);

        if (manualCount > purchaseAmount) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MANUAL_QUANTITY.getMessage());
        }
        return manualCount;
    }

    private void validateNoDuplicateBonusNumber(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }

    private List<LottoNumber> validateLottoIntegrity(List<String> strings) {
        try {
            return strings.stream()
                    .map(Integer::parseInt)
                    .map(LottoNumber::valueOf)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }
    }

    private int validateUnit(int purchasePrice) {
        if (purchasePrice % LOTTO_PRICE > 0) {
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

    private int validateIntegerInputIntegrity(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }
    }

    private void validateNonNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NEGATIVE_INPUT.getMessage());
        }
    }
}
