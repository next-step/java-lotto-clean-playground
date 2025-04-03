package controller;

import domain.*;
import factory.LottoGeneratorFactory;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGeneratorFactory generatorFactory;

    public LottoController(InputView inputView, OutputView outputView,LottoGeneratorFactory generatorFactory) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.generatorFactory = generatorFactory;
    }

    public void run() {
        long purchaseAmount = inputView.readPurchaseAmount();
        int manualCount = inputView.readManualLottoCount();

        List<Lotto> manualLottoList = inputView.readManualLottoNumbers(manualCount)
                .stream()
                .map(this::parseAndValidate)
                .collect(Collectors.toList());
        LottoPurchase lottoList = new LottoPurchase(purchaseAmount, manualLottoList, generatorFactory);
        outputView.printPurchaseResult(lottoList.getLottoCount(), lottoList);

        WinningLotto winningLotto = getWinningLotto();

        LottoResult lottoResult = LottoResult.from(lottoList.getLottoList(), winningLotto);
        outputView.printWinningStatisticsMessage();
        outputView.printWinningStatistics(lottoResult);
        printProfitRate(purchaseAmount, lottoResult.calculateTotalPrize());
    }

    private Lotto parseAndValidate(String numbers) {
        List<Integer> parsedNumbers = Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        return new Lotto(parsedNumbers.stream().map(LottoNumber::new).collect(Collectors.toList()));
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
