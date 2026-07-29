package domain.lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = List.copyOf(lottoNumbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
            if (lottoNumbers.size() != LOTTO_SIZE) {
                throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
            }
    }

    private void validateDuplicate(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> uniqueNumbers = new HashSet<>(lottoNumbers);

        if (uniqueNumbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public int matchCount(Lotto other) {
        return (int) lottoNumbers.stream()
                .filter(other.lottoNumbers::contains)
                .count();
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public void validateBonusNumber(LottoNumber bonusNumber) {
        if (contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
