package controller;

import model.Lotto;
import model.LottoGenerator;
import model.LottoNumber;
import model.Lottos;
import model.Money;
import model.Rank;
import model.WinningNumbers;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {
    public void lottoRun() {
        Lottos lottos = purchaseLottos();
        displayPurchasedLottos(lottos);

        WinningNumbers winningNumbers = createWinningNumbers();
        showWinningResults(lottos, winningNumbers);
        InputView.close();
    }

    private Lottos purchaseLottos() {
        int purchaseAmount = InputView.readAmount();
        Money money = new Money(purchaseAmount);

        List<Lotto> lottoList = new ArrayList<>();
        int countOfLottos = money.getCountOfLottos();

        for (int i = 0; i < countOfLottos; i++) {
            List<Integer> generatedNumbers = LottoGenerator.generate();
            lottoList.add(new Lotto(generatedNumbers));
        }

        return new Lottos(lottoList);
    }

    private void displayPurchasedLottos(Lottos lottos) {
        OutputView.printPurchaseCount(lottos.size());
        OutputView.printLottos(lottos);
    }

    private WinningNumbers createWinningNumbers() {
        String prizeNumbers = InputView.readWinningNumbers();
        List<LottoNumber> numbers = Arrays.stream(prizeNumbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new WinningNumbers(numbers);
    }

    private void showWinningResults(Lottos lottos, WinningNumbers winningNumbers) {
        Map<Rank, Integer> matchResult = lottos.calculateResult(winningNumbers);

        long totalPrize = calculateTotalPrize(matchResult);
        double rateOfReturn = (double) totalPrize / (lottos.size() * Money.LOTTO_PRICE);

        OutputView.printWinningResult(matchResult, rateOfReturn);
    }

    private long calculateTotalPrize(Map<Rank, Integer> matchResult) {
        long totalPrize = 0;
        for (Rank rank : matchResult.keySet()) {
            int count = matchResult.get(rank);
            totalPrize += (long) rank.getPrize() * count;
        }
        return totalPrize;
    }
}
