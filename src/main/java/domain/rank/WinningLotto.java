package domain.rank;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.LottoParser;
import java.util.List;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    private WinningLotto(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        validateDuplicate(winningNumbers, bonusNumber);
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(final String winningNumbersInput, final String bonusNumberInput) {
        LottoParser.validateEmpty(bonusNumberInput);
        List<LottoNumber> lottoNumbers = LottoParser.parseNumbers(winningNumbersInput);
        LottoNumber bonusNumber = LottoNumber.from(Integer.parseInt(bonusNumberInput));
        return new WinningLotto(lottoNumbers, bonusNumber);
    }

    public Lotto getWinningLotto() {
        return winningNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validateDuplicate(final List<LottoNumber> winningNumbers, final LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
