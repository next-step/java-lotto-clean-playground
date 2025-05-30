package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoController {
    public void run() {
        // 입력
        int money = InputView.getPurchaseAmount();
        int count = money / 1000;
        OutputView.printLottoAmount(count);

        // 로또 생성
        LottoList lottoList = LottoList.generateLottoList(count);
        OutputView.printLottoLists(lottoList);

        //지난주 로또 번호 받기
        List<Integer> winningNumbers = InputView.getLastWeekNumbers();

        //보너스볼 입력받기
        int bonusBallNumber = InputView.getBonusBallNumber();

        //각 로또와 winningnumber 비교해서 일치개수(matchCount)구하기
        Map<Prize, Integer> prizeCounts = new HashMap<>();
        for (Lotto lotto : lottoList.getLottoLists()) {
            int matchCount = (int) lotto.getNumberValues()
                                           .stream()
                                           .filter(winningNumbers::contains)
                                           .count();
            //보너스볼 일치 여부
            boolean bonusMatch = lotto.getNumberValues().contains(bonusBallNumber);

            //3이상 Prize객체로 변환
            Prize prize = Prize.of(matchCount,bonusMatch);

            if (prize != null) {
                prizeCounts.put(prize, prizeCounts.getOrDefault(prize, 0) + 1);
            }
        }

        //수익률 계산
        WinningStatistics statistics = new WinningStatistics(prizeCounts, money);

        //출력
        OutputView.printWinningStatics(statistics);


    }
}

