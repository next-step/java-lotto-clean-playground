package controller;

import domain.LottoNumber;
import view.OutputView;
import view.InputView;
import domain.TrialNumber;
import domain.RandomLottoNumberGenerator;
import domain.LottoNumberGenerator;

public class Controller {
    public void run(){
        // [1] 구입 금액을 입력 받는다.
        OutputView.printInputPurchaseAmount();
        int purchaseNumber = InputView.inputPurchaseMoney();
    //    System.out.println(purchaseNumber);
        // [2] 구입 금액으로 시도 횟수 만들기
        TrialNumber trialNumber = new TrialNumber(purchaseNumber);
        // [3] 시도 횟수 만큼 로또 번호 생성하기
        int lottoCount = trialNumber.getTrialNumber();
     //   System.out.println(lottoCount);
        // [4] 시도 횟수 만큼 랜덤 로또 생성;
        LottoNumberGenerator generator = new RandomLottoNumberGenerator();
        LottoNumber lottoNumber = new LottoNumber(trialNumber.getTrialNumber(), generator);
        OutputView.printLottos(lottoNumber);
        // [5] 지난주 당첨 번호 입력 받기

        // [6] 당첨번호와 로또 번호 비교해서 결과 탐색하기

        // [7] 결과 출력하기
    }
}
