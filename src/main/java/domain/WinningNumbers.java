package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WinningNumbers {
    private static final int WINNING_NUMBER_COUNT = 6;

    private final List<LottoNumber> winningNumbers = new ArrayList<>();

    public WinningNumbers(List<Integer> numbers) {
        if (numbers.size() != WINNING_NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }

        List<LottoNumber> lottoNumbers = convertToLottoNumbers(numbers);

        if (new HashSet<>(lottoNumbers).size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }

        winningNumbers.addAll(lottoNumbers);
    }

    public void validateBonusNumber(LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
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
}
