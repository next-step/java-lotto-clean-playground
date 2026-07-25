package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    public static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        validateLottoNumbers();
    }

    public List<LottoNumber> getLottoNumbers() {
        return new ArrayList<>(lottoNumbers);
    }

    private void validateLottoNumbers() {
        validateSize();
        validateDuplicate();
    }

    private void validateSize() {
            if (lottoNumbers.size() != LOTTO_SIZE) {
                throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
            }
    }

    private void validateDuplicate() {
        List<LottoNumber> temp = new ArrayList<>();

        for(LottoNumber number : lottoNumbers) {
            validateNumberDuplicate(number, temp);
            temp.add(number);
        }
    }

    private void validateNumberDuplicate(LottoNumber number, List<LottoNumber> temp) {
        if (temp.contains(number)) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public int matchCount(Lotto other) {
        int count = 0;

        for(LottoNumber number : lottoNumbers) {
            count += countMatch(number, other);
        }
        return count;
    }

    private int countMatch(LottoNumber number, Lotto other) {
        if (other.lottoNumbers.contains(number)) {
            return 1;
        }
        return 0;
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
