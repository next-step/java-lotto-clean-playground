package domain;

import enums.LottoType;

import java.util.List;

public class LottoWinningNumbers {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    private LottoWinningNumbers(List<Integer> numbers, int bonusNumber) {
        winningNumbers = Lotto.from(numbers, LottoType.WINNING);
        this.bonusNumber = LottoNumber.from(bonusNumber);
        validate();
    }

    public static LottoWinningNumbers from(List<Integer> numbers, int bonusNumbers) {
        return new LottoWinningNumbers(numbers, bonusNumbers);
    }

    public int matchCount(Lotto lotto) {
        return (int) lotto.getLottoNumbers().stream()
                .filter(number -> winningNumbers.getLottoNumbers().contains(number))
                .count();
    }

    public boolean bonusMatch(Lotto lotto) {
        return lotto.getLottoNumbers().contains(bonusNumber);
    }

    public Lotto getWinningNumbers() {
        return winningNumbers;
    }

    private void validate() {
        if (winningNumbers.getLottoNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
