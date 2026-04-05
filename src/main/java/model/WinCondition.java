package model;

import common.ValidateLotto;
import constants.ErrorMessageConstants;
import constants.LottoSettingsConstants;

import java.util.ArrayList;
import java.util.List;

public record WinCondition(List<Integer> numbers, int bonusNumber) {
    public WinCondition(List<Integer> numbers, int bonusNumber) {
        ValidateLotto.checkIfNumbersAreValid(numbers);
        this.checkBonusBall(bonusNumber);
        this.numbers = new ArrayList<>(numbers);
        this.bonusNumber= bonusNumber;
    }

    public List<Integer> numbers() {
        return List.copyOf(this.numbers);
    }

    private void checkBonusBall(int ballNumber) {
        if (ballNumber < LottoSettingsConstants.LOTTO_MINIMUM_NUMBER || ballNumber > LottoSettingsConstants.LOTTO_MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(ErrorMessageConstants.NUMBER_OUT_OF_RANGE);
        }
    }
}
