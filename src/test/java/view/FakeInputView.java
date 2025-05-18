package view;

import java.util.List;

public class FakeInputView implements InputView {

    private final List<String> manualLottoNumbers;

    public FakeInputView(List<String> manualLottoNumbers) {
        this.manualLottoNumbers = manualLottoNumbers;
    }

    @Override
    public String readLottoPurchaseAmount() {
        return "10000";
    }

    @Override
    public String readManualLottoCount() {
        return "2";
    }

    @Override
    public List<String> readManualLottoNumbers(int manualCount) {
        return manualLottoNumbers;
    }

    @Override
    public String readLastWeekWinningNumbers() {
        return "1,2,3,4,5,6";
    }

    @Override
    public String readBonusNumber() {
        return "7";
    }
}
