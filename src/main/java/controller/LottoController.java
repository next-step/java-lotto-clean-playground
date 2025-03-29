package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {

    private final LottoNumberGenerator generator;

    public LottoController(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public void run() {
        final long purchaseAmount = InputView.readPurchaseAmount();
        final LottoList lottoList = new LottoList(purchaseAmount, generator);

        OutputView.printPurchaseResult(lottoList.getLottoCount(), lottoList);

        WinningLottoNumbers winningLottoNumbers = new WinningLottoNumbers(InputView.readWinningNumbers());
        List<LottoNumber> winningNumbers = winningLottoNumbers.getNumbers();

        printWinningStatistics(lottoList, winningNumbers);
        printProfitRate(purchaseAmount,lottoList.calculatePrize(winningNumbers));
    }

    // 당첨 통계 계산 및 출력
    private void printWinningStatistics(LottoList lottoList, List<LottoNumber> winningNumbers) {
        OutputView.printWinningStatisticsMessage();
        PrizeCalculator PrizeCalculator = new PrizeCalculator();
        OutputView.printWinningStatistics(lottoList.calculateStatistics(winningNumbers), PrizeCalculator);
    }

    // 수익률 출력
    private void printProfitRate(long purchaseAmount, int totalPrize) {
        ProfitCalculator profitCalculator = new ProfitCalculator(purchaseAmount, totalPrize);
        double profitRate = profitCalculator.getProfitRate();
        OutputView.printProfitRate(profitRate);
    }
}
