package view;

import java.util.List;

public interface InputView {
    String readLottoPurchaseAmount();
    String readManualLottoCount();
    List<String> readManualLottoNumbers(int manualCount);
    String readLastWeekWinningNumbers();
    String readBonusNumber();
}
