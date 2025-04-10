package domain;


import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<LottoNumber> lottoNumbers) {
        validateNumberCount(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto of(List<Integer> lottoNumbers) {
        List<LottoNumber> lotto = lottoNumbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList());
        return new Lotto(lotto);
    }

    private void validateNumberCount(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers == null || lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> lottoNumbers) {
        if (new HashSet<>(lottoNumbers).size() != 6) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public int matchNumbers(Lotto winningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean hasBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public List<Integer> getNumbers() {
        return lottoNumbers.stream()
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toList());
    }

    private boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

}
