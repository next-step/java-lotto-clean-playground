package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class Controller {
    public void run() {
        TrialNumber trialNumber = getTrialNumber();
        LottoTickets lottoTickets = issueLottoTickets(trialNumber);
        Lotto winningLotto = getWinningLotto();
        calculateAndPrintResults(trialNumber, lottoTickets, winningLotto);
    }

    private TrialNumber getTrialNumber() {
        return retry(() -> {
            OutputView.printInputPurchaseAmount();
            int amount = InputView.inputPurchaseMoney();
            return new TrialNumber(amount);
        });
    }

    private LottoTickets issueLottoTickets(TrialNumber trialNumber) {
        int trialCount = trialNumber.getTrialNumber();
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberGenerator());
        List<Lotto> generatedLottos = lottoMachine.issue(trialCount);
        LottoTickets lottoTickets = new LottoTickets(generatedLottos);
        OutputView.printLottoNumber(lottoTickets, trialCount);
        return lottoTickets;
    }

    private Lotto getWinningLotto() {
        return retry(() -> {
            OutputView.printInputWinningNumber();
            List<Integer> inputWinningNumbers = InputView.inputWinningNumber();
            return new Lotto(inputWinningNumbers);
        });
    }

    private void calculateAndPrintResults(TrialNumber trialNumber, LottoTickets lottoTickets, Lotto winningLotto) {
        LottoResult statisticsResult = new LottoResult(lottoTickets, winningLotto);
        int purchaseAmount = trialNumber.getPurchaseAmount();
        OutputView.printWinningStatistics(statisticsResult, purchaseAmount);
    }

    private <T> T retry(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return retry(supplier);
        }
    }
}