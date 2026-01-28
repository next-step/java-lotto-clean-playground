package lotto.domain.model;

import java.util.List;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validate(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    public static Lotto from(List<Integer> rawNumbers) {
        List<LottoNumber> lottoNumbers = rawNumbers.stream()
                .map(LottoNumber::new)
                .toList();
        return new Lotto(lottoNumbers);
    }

    public List<LottoNumber> getNumbers() {
        return lottoNumbers;
    }

}
