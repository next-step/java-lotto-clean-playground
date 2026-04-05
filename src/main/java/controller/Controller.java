package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Controller {
    public void run() {
        TrialNumber trialNumber = getTrialNumber();

        int manualLottoNumberTrialCount = getManualLottoNumberTrialCount(); // 수동으로 받을 로또 번호
        List<Lotto> manualPurchaseLotto = getManualLottoNumber(manualLottoNumberTrialCount);// 수동으로 로또 번호 받을 티켓

        LottoTickets manualLottoTickets = new LottoTickets(manualPurchaseLotto);
        LottoTickets lottoTickets = issueLottoTickets(trialNumber);

        Lotto winningLotto = getWinningLotto();
        int bonusNumber = getBonusNumber();
        calculateAndPrintResults(trialNumber, lottoTickets, winningLotto, bonusNumber,manualLottoTickets);
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

    private List<Lotto> getManualLottoNumber(int manualLottoNumberTrialCount) {
        return retry(() -> {
            OutputView.printManualLottoTickets();
            return IntStream.range(0, manualLottoNumberTrialCount)
                    .mapToObj(i -> new Lotto(InputView.inputManualLottoNumber()))
                    .collect(Collectors.toList());
        });
    }


    private void calculateAndPrintResults(TrialNumber trialNumber, LottoTickets lottoTickets, Lotto winningLotto, int bonusNumber,LottoTickets manualLottoTickets) {
        LottoResult statisticsResult = new LottoResult(lottoTickets, winningLotto, bonusNumber,manualLottoTickets);
        int purchaseAmount = trialNumber.getPurchaseAmount();
        OutputView.printWinningStatistics(statisticsResult, purchaseAmount);
    }

    private int getBonusNumber() {
        return retry(() -> {
            OutputView.printBonusNumber();
            int bonusNumber = InputView.inputBonusNumber();
            return bonusNumber;
        });
    }

    private int getManualLottoNumberTrialCount() {
        return retry(() -> {
            OutputView.printManualTrialCount();
            int manualLottoNumberTrialCount = InputView.inputManualLottoNumberTrialCount();
            return manualLottoNumberTrialCount;
        });
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