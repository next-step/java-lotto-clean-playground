package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.WinningLotto;

public class LottoWinnerInputView {
    private final ViewInput input;

    public LottoWinnerInputView(ViewInput input) {
        this.input = input;
    }

    public WinningLotto readWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        LottoNumbers lottoNumbers = input.readLottoNumbers();

        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        LottoNumber bonusNumber = input.readBonusNumber();
        WinningLotto winning = new WinningLotto(lottoNumbers, bonusNumber);

        System.out.println();
        return winning;
    }
}
