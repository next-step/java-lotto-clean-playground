package domain;

import util.Errors;

public class ManualLottoCount {

    private final int value;

    public ManualLottoCount(int value, LottoPurchasePrice purchasePrice) {
        validate(value, purchasePrice);
        this.value = value;
    }

    private void validate(int value, LottoPurchasePrice lottoPurchasePrice) {
        final int maxLottoCount = lottoPurchasePrice.getLottoCount();
        validateCountOfManualLotto(value, maxLottoCount);
        validateCountOfManualIsNotNegative(value);
    }

    private void validateCountOfManualLotto(int value, int maxLottoCount) {
        if (value > maxLottoCount) {
            throw new IllegalArgumentException(Errors.INVALID_MANUAL_LOTTO_COUNT);
        }
    }

    private void validateCountOfManualIsNotNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(Errors.MANUAL_LOTTO_COUNT_IS_NEGATIVE);
        }
    }

}
