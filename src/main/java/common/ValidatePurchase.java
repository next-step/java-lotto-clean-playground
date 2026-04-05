package common;

import constants.ErrorMessageConstants;
import constants.LottoSettingsConstants;

public class ValidatePurchase {
    public static void validatePurchase(int userCashInput, int manualPurchaseCount){
        checkPriceHigherThanSingleLottoPrice(userCashInput);
        checkIfManualPurchaseIsWithinPrice(userCashInput, manualPurchaseCount);
    }
    private static void checkPriceHigherThanSingleLottoPrice(int price) {
       if (price < LottoSettingsConstants.LOTTO_PRICE){
           throw new IllegalArgumentException(ErrorMessageConstants.PRICE_TOO_LOW);
       }
    }

    private static void checkIfManualPurchaseIsWithinPrice(int price, int manualPurchaseCount){
        if (price < LottoSettingsConstants.LOTTO_PRICE * manualPurchaseCount){
           throw new IllegalArgumentException(ErrorMessageConstants.PRICE_NOT_ENOUGH_FOR_MANUAL_PURCHASE);
       }
    }
}
