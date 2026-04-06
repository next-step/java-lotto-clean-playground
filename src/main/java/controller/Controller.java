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
        // [1] 구입 금액을 입력 받는다.
        TrialNumber trialNumber = getTrialNumber();
        // [2] 수기로 입력할 로또 갯수를 입력 받는다.
        int manualTrialCount = getManualTrialCount();
        // [3] 수기로 입력 받을 로또 번호를 입력 받는다.
        LottoTickets manualTickets = getManualLottoTickets(manualTrialCount);

        // [4] 자동으로 뽑을 로또 번호 = 총 구입 금액으로 만든 시도 회숫 - 수기로 입력 받을  로또 번호
        int autoTrialCount = trialNumber.getTrialNumber() - manualTrialCount;
        // [5] 자동으로 로또 번호를 입력 받는다.
        LottoTickets autoTickets = issueLottoTickets(autoTrialCount, manualTrialCount);
        // [6] 지난주 로또 번호를 입력 받는다.
        Lotto winningLotto = getWinningLotto();
        // [7] 보너스 번호를 입력 받는다.
        int bonusNumber = getBonusNumber(winningLotto);
        // [8] 최종 결과를 계산한다.
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
            validateBonusNumber(winningLotto, bonusNumber);
            return bonusNumber;
        });
    }

    private LottoResult calculateAndPrintResults(LottoTickets autoTickets, Lotto winningLotto, int bonusNumber, LottoTickets manualTickets) {
        LottoResult statisticsResult = new LottoResult(autoTickets, winningLotto, bonusNumber, manualTickets);
        return statisticsResult ;
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