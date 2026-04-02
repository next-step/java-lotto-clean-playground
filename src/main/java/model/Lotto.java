package model;

import constants.ErrorMessageConstants;
import constants.LottoSettingsConstants;
import util.ValidateLotto;

import java.util.ArrayList;
import java.util.Arrays;
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

    public LottoResult calculateLottoResult(List<Integer> winningNumbers) {
        Set<Integer> lottoNumbers= new HashSet<>(this.numbers);
        Set<Integer> winningNumberSet = new HashSet<>(winningNumbers);
        lottoNumbers.retainAll(winningNumberSet);

        return Arrays.stream(LottoResult.values())
                .filter(result->lottoNumbers.size() == result.matchCount)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException(ErrorMessageConstants.NO_MATCHING_RESULT));
    }
}
