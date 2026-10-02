package domain;

import java.util.ArrayList;
import java.util.List;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> numbers, int number) {
        Lotto winningNumbers = Lotto.from(numbers);
        LottoNumber bonusNumber = new LottoNumber(number);

        validateDuplicateBonusNumber(winningNumbers, bonusNumber);

        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicateBonusNumber(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(
                    "보너스 볼은 당첨 번호와 중복될 수 없습니다."
            );
        }
    }
}
