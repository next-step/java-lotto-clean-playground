package model;

import exception.CustomException;
import exception.ErrorMessage;
import util.Parser;

public class ManualCount {

    private final int manualCount;

    public ManualCount(String inputCount, PurchaseAmount purchaseAmount) {
        this.manualCount = Parser.parseInt(inputCount);
        validate(manualCount, purchaseAmount);
    }

    public int getManualCount() {
        return manualCount;
    }

    private void validate(int manualCount, PurchaseAmount purchaseAmount) {
        validateManualCountNotNegative(manualCount);
        validateManualCountNotExceedTotal(manualCount, purchaseAmount);
    }

    private static void validateManualCountNotNegative(int manualCount) {
        if (manualCount < 0) {
            throw new CustomException(ErrorMessage.INVALID_MANUAL_COUNT_NEGATIVE);
        }
    }

    private static void validateManualCountNotExceedTotal(int manualCount, PurchaseAmount purchaseAmount) {
        if (manualCount > purchaseAmount.getTotalLottoCount()) {
            throw new CustomException(ErrorMessage.INVALID_MANUAL_COUNT_EXCEED);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(manualCount);
    }
}
