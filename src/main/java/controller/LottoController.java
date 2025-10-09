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
        try {
            int purchaseAmount = InputView.readAmount();
            Money money = new Money(purchaseAmount);

            int manualCount = InputView.readManualPurchase();
            money.validateManualLottoCount(manualCount);

            Lottos lottos = purchaseLottos(money, manualCount);
            displayPurchasedLottos(lottos, manualCount);

            WinningNumbers winningNumbers = createWinningNumbers();

            showWinningResults(lottos, winningNumbers);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        } finally {
            InputView.close();
        }
    }

    private Lottos purchaseLottos(Money money, int manualCount) {
        List<Lotto> lottoList = new ArrayList<>();

        addManualLottos(lottoList, manualCount);
        addAutoLottos(lottoList, money, manualCount);

        return new Lottos(lottoList);
    }

    private void addManualLottos(List<Lotto> lottoList, int manualCount) {
        OutputView.displayManualLottoPrompt();
        for (int i = 0; i < manualCount; i++) {
            List<Integer> lottoNumbers = InputView.readManualLotto();
            lottoList.add(new Lotto(lottoNumbers));
        }
    }

    private void addAutoLottos(List<Lotto> lottoList, Money money, int manualCount) {
        int autoLottos = money.getCountOfAutoLottos(manualCount);

        for (int i = 0; i < autoLottos; i++) {
            List<Integer> generatedNumbers = LottoGenerator.generate();
            lottoList.add(new Lotto(generatedNumbers));
        }
    }

    private void displayPurchasedLottos(Lottos lottos, int manualCount) {
        OutputView.printPurchaseCount(manualCount, lottos.size() - manualCount);
        OutputView.printLottos(lottos);
    }

    private WinningNumbers createWinningNumbers() {
        List<Integer> lottoNumbers = InputView.readWinningNumbers();
        WinningNumbers winningNumbers = new WinningNumbers(
                lottoNumbers.stream().map(LottoNumber::new).collect(Collectors.toList())
        );

        int bonusNumberInt = InputView.readBonusNumbers();
        winningNumbers.setBonusBall(new LottoNumber(bonusNumberInt));

        return winningNumbers;
    }

    private void showWinningResults(Lottos lottos, WinningNumbers winningNumbers) {
        Map<Rank, Integer> matchResult = lottos.calculateResult(winningNumbers);

        long totalPrize = lottos.calculateTotalPrize(matchResult);
        double rateOfReturn = lottos.calculateRateOfReturn(totalPrize);

        OutputView.printWinningResult(matchResult, rateOfReturn);
    }
}
