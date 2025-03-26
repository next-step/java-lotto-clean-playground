package controller;

import domain.LottoList;
import domain.ProfitCalculator;
import domain.RandomLottoNumberGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import view.InputView;
import view.OutputView;

public class LottoController {

    private final static int PRICE_PER_LOTTO = 1000;

    private final static RandomLottoNumberGenerator generator = new RandomLottoNumberGenerator();

    public void run() {
        final Integer purchaseAmount = InputView.readPurchaseAmount();
        final LottoList lottoList = createLottoList(purchaseAmount);

        OutputView.printPurchaseResult(purchaseAmount / PRICE_PER_LOTTO, lottoList);

        List<Integer> winningNumbers = getWinningNumbers();

        ProfitCalculator profitCalculator = new ProfitCalculator(purchaseAmount);
        printWinningStatistics(lottoList, winningNumbers, profitCalculator);
        printProfitRate(profitCalculator);
    }

    // 로또 생성
    private LottoList createLottoList(Integer purchaseAmount) {
        int lottoCount = purchaseAmount / PRICE_PER_LOTTO;
        return new LottoList(lottoCount, generator);
    }

    // 당첨 번호 입력 및 변환
    private List<Integer> getWinningNumbers() {
        String winningNumbersInput = InputView.readWinningNumbers();
        return convertInputToNumbers(winningNumbersInput);
    }

    // 당첨 통계 계산 및 출력
    private void printWinningStatistics(LottoList lottoList, List<Integer> winningNumbers, ProfitCalculator profitCalculator) {
        profitCalculator.calculateWinningStatistics(lottoList, winningNumbers);

        OutputView.printWinningStatisticsMessage();
        OutputView.printWinningStatistics(profitCalculator);
    }

    // 수익률 출력
    private void printProfitRate(ProfitCalculator profitCalculator) {
        double profitRate = profitCalculator.getProfitRate();
        OutputView.printProfitRate(profitRate);
    }

    private List<Integer> convertInputToNumbers(String winningNumbersInput) {
        return Arrays.stream(winningNumbersInput.split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
