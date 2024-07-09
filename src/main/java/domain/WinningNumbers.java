package domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningNumbers {
    private final Set<LottoNumber> numbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 정확히 6개여야 합니다.");
        }
        this.numbers = numbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toSet());
        if (this.numbers.size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 중복되지 않아야 합니다.");
        }
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public int countMatchingNumbers(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
            .filter(numbers::contains)
            .count();
    }

    public boolean containsBonusNumber(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }
}
