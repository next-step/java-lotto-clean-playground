package common;

import constants.ErrorMessageConstants;
import constants.LottoSettingsConstants;

public class ValidatePurchase {
    public static void checkIfPurchaseInfoIsValid(int userCashInput, int manualPurchaseCount){
        checkIfPriceHigherThanSingleLottoPrice(userCashInput);
        checkIfManualPurchaseIsWithinPrice(userCashInput, manualPurchaseCount);
    }
    private static void checkIfPriceHigherThanSingleLottoPrice(int price) {
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
