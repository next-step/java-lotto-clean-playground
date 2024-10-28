package domain;

import java.util.List;

public class ManualLottoNumbers {

    private final List<List<Integer>> manualNumbers;

    public ManualLottoNumbers(List<List<Integer>> manualNumbers) {
        this.manualNumbers = manualNumbers;
    }

    public List<List<Integer>> getManualNumbers() {
        return manualNumbers;
    }
}
