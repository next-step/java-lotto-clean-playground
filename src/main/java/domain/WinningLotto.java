package domain;

import java.util.*;
import java.util.stream.Collectors;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        validateInputs(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateInputs(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers == null || bonusNumber == null) {
            throw new IllegalArgumentException("로또와 보너스 번호는 null일 수 없습니다.");
        }
        if (winningNumbers.numbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public List<LottoNumber> getWinningNumbers() {
        return winningNumbers.numbers().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
