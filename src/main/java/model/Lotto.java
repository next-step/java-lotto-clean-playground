package model;

import java.util.Collections;
import java.util.List;

import exception.CustomException;
import exception.ErrorMessage;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    private static final int LOTTO_NUMBERS_LENGTH = 6;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);

        this.lottoNumbers = Collections.unmodifiableList(lottoNumbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    private void validate(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers == null || lottoNumbers.size() != LOTTO_NUMBERS_LENGTH) {
            throw new CustomException(ErrorMessage.INVALID_NUMBERS_SIZE);
        }
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
