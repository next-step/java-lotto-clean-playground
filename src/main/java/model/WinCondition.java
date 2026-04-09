package model;

import common.ValidateLotto;
import constants.ErrorMessageConstants;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinCondition {
    private final Lotto winningLotto;
    private final int bonusNumber;
    public WinCondition(Lotto winningLotto, int bonusNumber) {
        this.checkBonusBall(bonusNumber, winningLotto.numbers());
        this.winningLotto= winningLotto;
        this.bonusNumber= bonusNumber;
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

    public LottoResult calculateLottoResultResult(Lotto lotto) {
        Set<Integer> lottoNumbers= new HashSet<>(this.winningLotto.numbers());
        Set<Integer> winningNumberSet = new HashSet<>(lotto.numbers());
        lottoNumbers.retainAll(winningNumberSet);

        return LottoResult.calculateLottoResult(lottoNumbers.size(), Collections.frequency(lotto.numbers(), this.bonusNumber));
    }

}
