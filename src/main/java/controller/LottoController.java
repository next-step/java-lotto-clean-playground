package controller;

import domain.Lotto;
import domain.Lottos;
import domain.Statistics;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private static final int LOTTO_PRICE = 1000;

    public void buyLottos() {

        int price = InputView.readPrice();
        int numberOfLottos = price / LOTTO_PRICE; //총 로또 구매 개수
        int numberOfHandLottos = InputView.numberOfhandLottos();
        int numberOfAutoLottos = numberOfLottos - numberOfHandLottos;

        //수동 로또 번호 입력
        List<Lotto> handLottos = InputView.handLottosNumber(numberOfHandLottos);

        //자동 로또 번호 생성
        //Lottos autoLottos = new Lottos(numberOfAutoLottos);
        Lottos autoLottos = Lottos.fromAutoLottos(numberOfAutoLottos);

        //로또 합치기
        List<Lotto> finalLottos = new ArrayList<>(handLottos);
        finalLottos.addAll(autoLottos.getLottos());

        //로또 정보 출력
        OutputView.printNumberOfLottos(numberOfHandLottos, numberOfAutoLottos);
        OutputView.printLottos(finalLottos);

        //당첨 번호
        List<Integer> winningNumbers = InputView.lottoWinningNumber();// 로또 당첨번호 입력
        int bonusNumber = InputView.bonusBall();

        //통계 계산
        Statistics statistics = new Statistics();
        for (Lotto lotto : finalLottos) {
            int matchedCount = lotto.countMatches(winningNumbers);
            boolean isBonusMatch = lotto.contains(bonusNumber);
            statistics.matchCountUpdate(matchedCount, isBonusMatch);  // 통계 업데이트
        }

        //출력
        OutputView.printWinningStatistics(statistics.getMatchCounts());


        double returnRate = statistics.calculateReturnRate(price);
        OutputView.printReturnRate(returnRate);
    }


}
