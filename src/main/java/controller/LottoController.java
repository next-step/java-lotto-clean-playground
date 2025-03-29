package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoController {

    private final LottoNumberGenerator generator;

    public LottoController(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public void run() {
        final Integer purchaseAmount = InputView.readPurchaseAmount();
        final LottoList lottoList = new LottoList(purchaseAmount, generator);

        OutputView.printPurchaseResult(lottoList.getLottoCount(), lottoList);

        List<LottoNumber> winningNumbers = getWinningNumbers();

        printWinningStatistics(lottoList, winningNumbers);
        printProfitRate(purchaseAmount,lottoList.calculatePrize(winningNumbers));
    }

    // 당첨 번호 입력 및 변환
    private List<LottoNumber> getWinningNumbers() {
        String winningNumbersInput = InputView.readWinningNumbers();
        return convertInputToNumbers(winningNumbersInput);
    }

    private List<LottoNumber> convertInputToNumbers(String winningNumbersInput) {
        return Stream.of(winningNumbersInput.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    // 당첨 통계 계산 및 출력
    private void printWinningStatistics(LottoList lottoList, List<LottoNumber> winningNumbers) {
        OutputView.printWinningStatisticsMessage();
        PrizeCalculator PrizeCalculator = new PrizeCalculator();
        OutputView.printWinningStatistics(lottoList.calculateStatistics(winningNumbers), PrizeCalculator);
    }

    // 수익률 출력
    private void printProfitRate(Integer purchaseAmount, int totalPrize) {
        ProfitCalculator profitCalculator = new ProfitCalculator(purchaseAmount, totalPrize);
        double profitRate = profitCalculator.getProfitRate();
        OutputView.printProfitRate(profitRate);
    }
}
