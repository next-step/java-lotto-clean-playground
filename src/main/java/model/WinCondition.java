package model;

import common.ValidateLotto;
import constants.ErrorMessageConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record WinCondition(List<Integer> numbers, int bonusNumber) {
    public WinCondition(List<Integer> numbers, int bonusNumber) {
        ValidateLotto.checkIfNumbersAreValid(numbers);
        this.checkBonusBall(bonusNumber, numbers);
        this.numbers = new ArrayList<>(numbers);
        this.bonusNumber= bonusNumber;
    }

    public List<Integer> numbers() {
        return List.copyOf(this.numbers);
    }

    private void checkBonusBall(int ballNumber, List<Integer> numbers) {
        checkIfNumberInRange(ballNumber);
        checkIfBonusNumberIsInNumbers(ballNumber, numbers);
    }

    private void checkIfNumberInRange(int ballNumber){
        ValidateLotto.checkIfNumbersAreInLottoNumberRange(List.of(ballNumber));
    }

    private void checkIfBonusNumberIsInNumbers(int ballNumber, List<Integer> numbers){
        if (Collections.frequency(numbers, ballNumber) > 0) {
            throw new IllegalArgumentException(ErrorMessageConstants.BONUS_NUMBER_IN_WINNING_NUMBER);
        }
    }
}
