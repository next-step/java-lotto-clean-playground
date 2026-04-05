package model;

import common.ValidateLotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
       ValidateLotto.checkIfNumbersAreValid(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    public List<Integer> getNumbers() {
        return List.copyOf(this.numbers);
    }

    public LottoResult compareWithWinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        Set<Integer> lottoNumbers= new HashSet<>(this.numbers);
        Set<Integer> winningNumberSet = new HashSet<>(winningNumbers);
        lottoNumbers.retainAll(winningNumberSet);

        return LottoResult.calculateLottoResult(lottoNumbers.size(), Collections.frequency(this.numbers, bonusNumber));
    }
}
