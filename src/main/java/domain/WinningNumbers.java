package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WinningNumbers {
    private static final int WINNING_NUMBER_COUNT = 6;

    private final List<LottoNumber> winningNumbers = new ArrayList<>();

    public WinningNumbers(List<Integer> numbers) {
        validateWinningNumberCount(numbers);

        List<LottoNumber> lottoNumbers = convertToLottoNumbers(numbers);

        validateDuplicateWinningNumbers(lottoNumbers);

        winningNumbers.addAll(lottoNumbers);
    }

    public boolean contains(LottoNumber number) {
        return winningNumbers.contains(number);
    }

    int countMatchingNumbers(Lotto lotto) {
        int count = 0;

        for (LottoNumber lottoNumber : lotto.getNumbers()) {
            count += countMatch(lottoNumber);
        }

        return count;
    }

    private int countMatch(LottoNumber lottoNumber) {
        if (isWinningNumber(lottoNumber)) {
            return 1;
        }

        return 0;
    }

    private boolean isWinningNumber(LottoNumber lottoNumber) {
        return winningNumbers.contains(lottoNumber);
    }

    private List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }

        return lottoNumbers;
    }

    private void validateWinningNumberCount(List<Integer> numbers) {
        if (numbers.size() != WINNING_NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicateWinningNumbers(List<LottoNumber> lottoNumbers) {
        if (new HashSet<>(lottoNumbers).size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }
    }
}
