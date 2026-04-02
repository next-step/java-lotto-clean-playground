package util;

import constants.ErrorMessageConstants;
import constants.LottoSettingsConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidateLotto {
    public static void checkIfNumbersAreValid(List<Integer> numbers) {
        checkIfDuplicateExist(numbers);
        checkIfNotEnoughNumbers(numbers);
        checkIfTooManyNumbers(numbers);
        checkIfNumbersAreInRange(numbers);
    }

    private static void checkIfNumbersAreInRange(List<Integer> numbers){
        List<Integer> notInRange = numbers.stream().filter(
                i-> i < LottoSettingsConstants.LOTTO_MINIMUM_NUMBER || i > LottoSettingsConstants.LOTTO_MAXIMUM_NUMBER
        ).toList();

        if (!notInRange.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessageConstants.NUMBER_OUT_OF_RANGE);
        }
    }

    private static void checkIfNotEnoughNumbers(List<Integer> numbers){
        if (numbers.size() < LottoSettingsConstants.LOTTO_SIZE) {
            throw new IllegalArgumentException(ErrorMessageConstants.NUMBER_TOO_LITTLE);
        }
    }

    private static void checkIfTooManyNumbers(List<Integer> numbers){
        if (numbers.size() > LottoSettingsConstants.LOTTO_SIZE) {
            throw new IllegalArgumentException(ErrorMessageConstants.NUMBER_TOO_MANY);
        }
    }

    protected static void checkIfDuplicateExist(List<Integer> numbers) {
        Set<Integer> test = new HashSet<>(numbers);

        if (test.size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessageConstants.NO_DUPLICATES_ALLOWED);
        }
    }

    public static void checkPriceHigherThanSingleLottoPrice(int price) {
       if (price < LottoSettingsConstants.LOTTO_PRICE){
           throw new IllegalArgumentException(ErrorMessageConstants.PRICE_TOO_LOW);
       }
    }
}
