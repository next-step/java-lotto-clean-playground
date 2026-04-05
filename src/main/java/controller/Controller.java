package controller;


import domain.Lotto;
import domain.LottoMachine;
import domain.LottoTickets;
import domain.TrialNumber;
import domain.LottoResult;
import domain.RandomLottoNumberGenerator;

import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Controller {

    public void run() {
        TrialNumber trialNumber = getTrialNumber();

        int manualTrialCount = getManualTrialCount();
        LottoTickets manualTickets = getManualLottoTickets(manualTrialCount);

        int autoTrialCount = trialNumber.getTrialNumber() - manualTrialCount;
        LottoTickets autoTickets = issueLottoTickets(autoTrialCount);

        Lotto winningLotto = getWinningLotto();
        int bonusNumber = getBonusNumber(winningLotto);

        calculateAndPrintResults(trialNumber, autoTickets, winningLotto, bonusNumber, manualTickets);
    }

    private TrialNumber getTrialNumber() {
        return retry(() -> {
            OutputView.printInputPurchaseAmount();
            return new TrialNumber(InputView.inputPurchaseMoney());
        });
    }

    private int getManualTrialCount() {
        return retry(() -> {
            OutputView.printManualTrialCount();
            return InputView.inputManualLottoNumberTrialCount();
        });
    }

    private LottoTickets getManualLottoTickets(int trialCount) {
        return retry(() -> {
            OutputView.printManualLottoTickets();
            List<Lotto> manualLottos = IntStream.range(0, trialCount)
                    .mapToObj(i -> new Lotto(InputView.inputManualLottoNumber()))
                    .collect(Collectors.toList());
            return new LottoTickets(manualLottos);
        });
    }

    private LottoTickets issueLottoTickets(int trialCount) {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberGenerator());
        List<Lotto> generatedLottos = lottoMachine.issue(trialCount);
        LottoTickets lottoTickets = new LottoTickets(generatedLottos);

        OutputView.printLottoNumber(lottoTickets, trialCount);
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
            validateBonusNumber(winningLotto, bonusNumber);
            return bonusNumber;
        });
    }

    private void calculateAndPrintResults(TrialNumber trialNumber, LottoTickets autoTickets, Lotto winningLotto, int bonusNumber, LottoTickets manualTickets) {
        LottoResult statisticsResult = new LottoResult(autoTickets, winningLotto, bonusNumber, manualTickets);
        OutputView.printWinningStatistics(statisticsResult, trialNumber.getPurchaseAmount());
    }

    private void validateBonusNumber(Lotto winningLotto, int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        if (winningLotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
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