package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class Controller {
    public void run() {
        // [1], [2] 구입 금액 입력 및 시도 횟수 생성 (예외 발생 시 재입력)
        TrialNumber trialNumber = retry(() -> {
            OutputView.printInputPurchaseAmount();
            int amount = InputView.inputPurchaseMoney();
            return new TrialNumber(amount);
        });

        int trialCount = trialNumber.getTrialNumber();
        // [3] 로또 머신을 통한 로또 발행 및 티켓 관리 객체 초기화
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberGenerator());
        List<Lotto> generatedLottos = lottoMachine.issue(trialCount);
        LottoTickets lottoTickets = new LottoTickets(generatedLottos);

        OutputView.printLottoNumber(lottoTickets, trialCount);

        // [4] 지난주 당첨 번호 입력 받기 및 Lotto 객체로 포장 (타입 오류 수정, 재입력 적용)
        Lotto winningLotto = retry(() -> {
            OutputView.printInputWinningNumber();
            List<Integer> inputWinningNumbers = InputView.inputWinningNumber();
            return new Lotto(inputWinningNumbers);
        });

        // [5] 당첨 번호와 로또 번호 비교해서 결과 탐색하기
        LottoResult statisticsResult = new LottoResult(lottoTickets, winningLotto);

        // [6] 결과 출력하기
        int purchaseAmount = trialNumber.getPurchaseAmount();
        OutputView.printWinningStatistics(statisticsResult, purchaseAmount);
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
