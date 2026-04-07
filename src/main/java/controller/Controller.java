package controller;

import domain.Lotto;
import domain.LottoMachine;
import domain.LottoTickets;
import domain.TrialNumber;
import domain.LottoResult;
import domain.RandomLottoNumberGenerator;
import domain.validator.BonusNumberValidator;
import domain.validator.ManualCountValidator;

import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Controller {

    public void run() {
        TrialNumber trialNumber = getTrialNumber();
        int manualTrialCount = getManualTrialCount(trialNumber.getTrialCount());
        LottoTickets manualTickets = getManualLottoTickets(manualTrialCount);
        int autoTrialCount = trialNumber.getTrialCount() - manualTrialCount;
        LottoTickets autoTickets = issueLottoTickets(autoTrialCount, manualTrialCount);
        Lotto winningLotto = getWinningLotto();
        int bonusNumber = getBonusNumber(winningLotto);
        LottoResult statisticsResult = calculateAndPrintResults(autoTickets, winningLotto, bonusNumber, manualTickets);
        OutputView.printWinningStatistics(statisticsResult, trialNumber.getPurchaseAmount());
    }

    private TrialNumber getTrialNumber() {
        return retry(() -> {
            OutputView.printInputPurchaseAmount();
            return new TrialNumber(InputView.inputPurchaseMoney());
        });
    }

    private int getManualTrialCount(int totalTrialCount) {
        return retry(() -> {
            OutputView.printManualTrialCount();
            int manualCount = InputView.inputManualLottoNumberTrialCount();
            ManualCountValidator.validate(totalTrialCount, manualCount);
            return manualCount;
        });
    }

    private LottoTickets getManualLottoTickets(int trialCount) {
        if (trialCount == 0) {
            return new LottoTickets(List.of());
        }
        return retry(() -> {
            OutputView.printManualLottoTickets();
            List<Lotto> manualLottos = IntStream.range(0, trialCount)
                    .mapToObj(i -> new Lotto(InputView.inputManualLottoNumber()))
                    .collect(Collectors.toList());
            return new LottoTickets(manualLottos);
        });
    }

    private LottoTickets issueLottoTickets(int trialCount, int manualCount) {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberGenerator());
        List<Lotto> generatedLottos = lottoMachine.issue(trialCount);
        LottoTickets lottoTickets = new LottoTickets(generatedLottos);

        OutputView.printLottoNumber(lottoTickets, trialCount, manualCount);
        return lottoTickets;
    }

    private Lotto getWinningLotto() {
        return retry(() -> {
            OutputView.printInputWinningNumber();
            return new Lotto(InputView.inputWinningNumber());
        });
    }

    private int getBonusNumber(Lotto winningLotto) {
        return retry(() -> {
            OutputView.printBonusNumber();
            int bonusNumber = InputView.inputBonusNumber();
            BonusNumberValidator.validate(winningLotto, bonusNumber);
            return bonusNumber;
        });
    }

    private LottoResult calculateAndPrintResults(LottoTickets autoTickets, Lotto winningLotto, int bonusNumber, LottoTickets manualTickets) {
        return new LottoResult(autoTickets, winningLotto, bonusNumber, manualTickets);
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
