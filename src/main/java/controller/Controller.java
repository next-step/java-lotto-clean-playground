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
        // [1] 구입 금액을 입력 받는다.
        TrialNumber trialNumber = getTrialNumber();
        // [2] 수기로 입력할 로또 갯수를 입력 받는다.
        int manualTrialCount = getManualTrialCount(trialNumber.getTrialNumber());
        // [3] 수기로 입력 받을 로또 번호를 입력 받는다.
        LottoTickets manualTickets = getManualLottoTickets(manualTrialCount);
        // [4] 자동으로 생성할 로또 번호를 계산한다.
        int autoTrialCount = trialNumber.getTrialNumber() - manualTrialCount;
        // [5] 자동 로또 번호를 생성한다.
        LottoTickets autoTickets = issueLottoTickets(autoTrialCount, manualTrialCount);
        // [6] 우승 로또 번호를 입력 받는다.
        Lotto winningLotto = getWinningLotto();
        // [7] 보너스 번호를 입력받는다.
        int bonusNumber = getBonusNumber(winningLotto);
        // [8] 로또 결과를 계산한다.
        LottoResult statisticsResult = calculateAndPrintResults(autoTickets, winningLotto, bonusNumber, manualTickets);
        // [9] 최종 결과를 출력한다.
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