package domain;

import java.util.*;
import java.util.stream.Collectors;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateInputs(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateInputs(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto == null || bonusNumber == null) {
            throw new IllegalArgumentException("로또와 보너스 번호는 null일 수 없습니다.");
        }
        if (lotto.numbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public List<LottoNumber> getNumbers() {
        return lotto.numbers().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
