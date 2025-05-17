package domain;

import exception.InvalidLottoNumberCountException;
import exception.LottoNumberDuplicationException;
import java.util.HashSet;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private final LottoNumbers lottoNumbers;

    public Lotto(LottoNumbers lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public LottoNumbers getNumbers() {
        return lottoNumbers;
    }

    private void validate(LottoNumbers lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new InvalidLottoNumberCountException("로또 숫자 개수가 6개가 아닙니다.");
        }

        if (new HashSet<>(lottoNumbers.getLottoNumbers().stream().map(LottoNumber::getNumber).toList()).size()
                != LOTTO_NUMBER_COUNT) {
            throw new LottoNumberDuplicationException("로또의 숫자가 중복됩니다.");
        }
    }

    public int getMatchCount(Lotto purchaseLotto, Lotto winningLotto) {
        return (int) purchaseLotto.getNumbers().getLottoNumbers().stream()
                .filter(number -> winningLotto.getNumbers().getLottoNumbers().contains(number))
                .count();
    }
}
