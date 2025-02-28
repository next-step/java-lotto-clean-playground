package domain;

import java.util.List;

public class WinningLottoNumbers {
    private final List<LottoNumber> numbers;
    private final BonusBall bonusBall;

    public WinningLottoNumbers(List<LottoNumber> numbers, BonusBall bonusBall) {
        this.numbers = List.copyOf(numbers);
        this.bonusBall = bonusBall;
    }

    private void validateBonusBallNotDuplicate(List<Integer> numbers, int bonusBall) {
        if (numbers.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 볼은 기존 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public BonusBall getBonusBall() {
        return bonusBall;
    }
}
