package controller;

import domain.Lotto;
import domain.Lottos;
import domain.RandomNumberGenerater;
import domain.Statistics;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private static final int LOTTO_PRICE = 1000;

    public void buyLottos(){

        int price = InputView.readPrice();
        int numberOfLottos = price / LOTTO_PRICE; //로또 구매 개수
        OutputView.printNumberOfLottos(numberOfLottos);

        Lottos lottos = new Lottos(numberOfLottos);  // 로또 자동 생성
        OutputView.printLottos(lottos);

        List<Integer> winningNumbers = InputView.LottoWinningNumber(numberOfLottos);// 로또 당첨번호 입력
        int bonusNumber = InputView.BonusBall();

        //통계 계산
        Statistics statistics = new Statistics();
        for (Lotto lotto : lottos.getLottos()) {
            int matchedCount = lotto.countMatches(winningNumbers);
            boolean isBonusMatch = lotto.contains(bonusNumber);
            statistics.updateStatistics(matchedCount, isBonusMatch);  // 통계 업데이트
        }

        //출력
        OutputView.printWinningStatistics(statistics.getMatchCounts());


        double returnRate = statistics.calculateReturnRate(price);
        OutputView.printReturnRate(returnRate);
    }


}
