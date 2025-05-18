package service;

import domain.BonusNumber;
import domain.Lottos;
import domain.WinningLotto;

public interface InputHandler {
    int readPurchaseAmount();
    int readManualLottoCount(int purchaseAmount);
    Lottos readManualLottos(int count);
    WinningLotto readWinningNumbers();
    BonusNumber readBonusNumber(WinningLotto winningLotto);
}
