package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class WinningNumbers {
    private static final int WINNING_NUMBER_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Integer> winningNumbers;

    public WinningNumbers(List<Integer> winningNumbers) {
        validate(winningNumbers);
        this.winningNumbers = new ArrayList<>(winningNumbers);
    }

    private void validate(List<Integer> winningNumbers) {
        if (winningNumbers.size() != WINNING_NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
        if (new HashSet<>(winningNumbers).size() != winningNumbers.size()) {
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }
        for (int number : winningNumbers) {
            validateRange(number);
        }
    }

    private void validateRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("당첨 번호는 1~45 사이여야 합니다.");
        }
    }

    public Integer countMatches(Lotto lotto) {
        int count = 0;
        for (int number : lotto.getLottoNumbers()) {
            count += matchWeight(number);
        }
        return count;
    }

    private int matchWeight(int number) {
        if (winningNumbers.contains(number)) {
            return 1;
        }
        return 0;
    }

    public List<Integer> getWinningNumbers() {
        return Collections.unmodifiableList(winningNumbers);
    }
}
