package lotto;

import java.util.List;

public record WinningLotto(LottoNumbers numbers, LottoNumber bonus) {
    public WinningLotto {
        if (numbers.value().contains(bonus)) {
            throw new LottoException.DuplicateNumber();
        }
    }

    public List<LottoNumber> getNumbers() {
        return numbers.value();
    }
}
