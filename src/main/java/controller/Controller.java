package controller;

import domain.*;
import view.OutputView;
import view.InputView;
import domain.Lotto;
import java.util.List;

public class Controller {
    public void run() {
        // [1] 구입 금액을 입력 받는다.
        OutputView.printInputPurchaseAmount();
        int purchaseNumber = InputView.inputPurchaseMoney();
        // [2] 구입 금액으로 시도 횟수 만들기
        TrialNumber trialNumber = new TrialNumber(purchaseNumber);
        // [3] 시도 횟수 만큼 랜덤 로또 생성;
        LottoNumberGenerator generator = new RandomLottoNumberGenerator();
        int trialCount = trialNumber.getTrialNumber();
        LottoTickets lottoNumber = new LottoTickets(trialCount, generator);
        OutputView.printLottoNumber(lottoNumber,trialCount);
        // [4] 지난주 당첨 번호 입력 받기
        OutputView.printInputWinningNumber();
        List<Integer> inputWinningNumbers = InputView.inputWinningNumber();
        Lotto winningNumbers = new Lotto(inputWinningNumbers);
        // [5] 당첨번호와 로또 번호 비교해서 결과 탐색하기
        LottoResult statisticsResult = new LottoResult(lottoNumber, winningNumbers);
        // [6] 결과 출력하기
        int purchaseAmount = trialNumber.getPurchaseAmount();
        OutputView.printWinningStatistics(statisticsResult, purchaseAmount);
    }
}


