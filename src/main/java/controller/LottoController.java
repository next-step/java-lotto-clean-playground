package controller;

import domain.*;
import domain.generator.AutoLottoGenerator;
import domain.generator.LottoGenerator;
import domain.generator.ManualLottoGenerator;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        long purchaseAmount = inputView.readPurchaseAmount();
        final int manualLottoCount = inputView.readManualLottoCount();

        List<String> manualLottoInputs = inputView.readManualLottoNumbers(manualLottoCount);
        LottoGenerator manualGenerator = new ManualLottoGenerator(manualLottoInputs);
        LottoGenerator autoGenerator = new AutoLottoGenerator();
        LottoList lottoList = LottoList.create(purchaseAmount, manualLottoCount,manualGenerator, autoGenerator);
        outputView.printPurchaseResult(lottoList.getLottoCount(), lottoList);

        WinningLotto winningLotto = getWinningLotto();
        LottoResult lottoResult = LottoResult.from(lottoList.getLottoList(), winningLotto);
        outputView.printWinningStatisticsMessage();
        outputView.printWinningStatistics(lottoResult);

        printProfitRate(purchaseAmount, lottoResult.calculateTotalPrize());
    }

    private WinningLotto getWinningLotto() {
        String winningNumbersInput = inputView.readWinningNumbers();

        List<LottoNumber> winningNumbers = Arrays.stream(winningNumbersInput.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        LottoNumber bonusNumber = new LottoNumber(inputView.readBonusNumber());
        return new WinningLotto(new Lotto(winningNumbers), bonusNumber);
    }

    private void printProfitRate(long purchaseAmount, long totalPrize) {
        Profit profitCalculator = new Profit(purchaseAmount, totalPrize);
        double profitRate = profitCalculator.getProfitRate();
        outputView.printProfitRate(profitRate);
    }
}
