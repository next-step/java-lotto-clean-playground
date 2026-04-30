package domain;

import java.util.List;

public class WinningNumbers {
    private static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(List<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers, bonusNumber);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers == null || numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers, LottoNumber bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }

        long distinctCount = numbers.stream()
                .distinct()
                .count();
        if (distinctCount != LOTTO_SIZE) {
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }
    }
}
