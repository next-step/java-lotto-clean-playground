package service;

import domain.BonusNumber;
import domain.Lottos;
import domain.WinningNumbers;

public interface InputHandler {
    int readPurchaseAmount();
    int readManualLottoCount(int purchaseAmount);
    Lottos readManualLottos(int count);
    WinningNumbers readWinningNumbers();
    BonusNumber readBonusNumber(WinningNumbers winningNumbers);
}

