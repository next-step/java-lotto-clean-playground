package controller;

import model.Lotto;
import model.RankCalculator;
import view.LottoInput;
import view.LottoOutput;

public class LottoController {
    public void run(Lotto lotto) {
        final var manualLottoCount = LottoInput.inputManualLottoCount();
        final var manualLottoNumbers = LottoInput.inputManualLottoNumbers(manualLottoCount);

        lotto.calcLottoNumbersCount(manualLottoCount);
        lotto.addManualLottoNumbers(manualLottoNumbers);
        lotto.generateRandomLottoNumbers();
        LottoOutput.printLottoNumbers(lotto);

        final var ansNumber = LottoInput.inputLottoAnswer();
        final var bonusBall = LottoInput.inputBonusBall();

        final var rankCalculator = new RankCalculator(ansNumber, bonusBall);
        rankCalculator.calculateAllLottoCorrectNumbers(lotto);
        LottoOutput.printCorrectNumbers(rankCalculator);

        double rate = rankCalculator.calculateRateOfReturn(lotto.getBalance());
        LottoOutput.printRateOfReturn(rate);
    }
}
