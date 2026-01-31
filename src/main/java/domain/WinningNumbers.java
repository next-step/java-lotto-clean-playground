package domain;

import java.util.List;

public class WinningNumbers {
    private final LottoNumbers numbers;
    private final int bonusNumber;

    private WinningNumbers(List<Integer> numbers, int bonusNumber) {
        this.numbers = new LottoNumbers(numbers);
        validateBonus(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumbers of(List<Integer> numbers, int bonusNumber) {
        return new WinningNumbers(numbers, bonusNumber);
    }

    public boolean contains(int n) {
        return numbers.contains(n);
    }
    public boolean bonusMatched(LottoTicket ticket) {
        return ticket.numbers().contains(bonusNumber);
    }

    private void validateBonus(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("보너스 번호는 1~45 범위여야 합니다.");
        }
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
