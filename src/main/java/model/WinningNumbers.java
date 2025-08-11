package model;

import exception.CustomException;
import exception.ErrorMessage;
import util.Parser;

import java.util.List;

public class WinningNumbers {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningNumbers(String inputWinningNumbers, String inputBonusNumber) {
        List<LottoNumber> parsedNumbers = Parser.parseManualLottoNumbers(inputWinningNumbers);

        this.winningLotto = new Lotto(parsedNumbers);
        this.bonusNumber = new LottoNumber(inputBonusNumber);

        validateBonusNumber(this.bonusNumber, this.winningLotto);
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validateBonusNumber(LottoNumber bonusNumber, Lotto winningLotto) {
        if (winningLotto.getLottoNumbers().contains(bonusNumber)) {
            throw new CustomException(ErrorMessage.DUPLICATE_BONUS_NUMBER);
        }
    }
}
