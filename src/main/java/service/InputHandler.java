package service;

import domain.Lottos;
import domain.WinningLotto;
import domain.LottoNumber;

public interface InputHandler {
    int readPurchaseAmount();
    int readManualLottoCount(int purchaseAmount);
    Lottos readManualLottos(int count);
    WinningLotto readWinningNumbers();
    LottoNumber readBonusNumber(WinningLotto winningLotto);
}
