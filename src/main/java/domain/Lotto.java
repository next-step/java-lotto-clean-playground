package domain;

import domain.validator.LottoNumberValidator;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private static final int LOTTO_SIZE = 6;

    private final List<Integer> lottoNumber;

    public Lotto(List<Integer> lottoNumber) {
        validate(lottoNumber);
        validateRange(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validate(List<Integer> lottoNumber) {
        if (lottoNumber.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        if (new HashSet<>(lottoNumber).size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }

    private void validateRange(List<Integer> lottoNumber) {
        lottoNumber.forEach(LottoNumberValidator::validateRange);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(lottoNumber);
    }
}
