package lotto;

import java.util.HashSet;
import java.util.List;

public record LottoNumbers(List<LottoNumber> value) {
    public static final int NUMBER_COUNT = 6;

    public LottoNumbers {
        if (value.size() != NUMBER_COUNT) {
            throw new LottoException.WrongNumberCount();
        }

        if (new HashSet<>(value).size() != value.size()) {
            throw new LottoException.DuplicateNumber();
        }
    }
}
