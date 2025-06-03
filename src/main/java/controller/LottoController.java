package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static domain.Prize.calculatePrize;

public class LottoController {
    public void run() {
        // 입력
        Money money = InputView.getPurchaseAmount();

        //수동 로또 갯수, 리스트 입력
        int manualLottoAmount = InputView.getManualLottoAmount();
        List<List<Integer>> manualNumbers = InputView.getManualLottoNumbers(manualLottoAmount);

        int count = money.autoTicketCount(manualLottoAmount);

        // 로또리스트 생성
        LottoList lottoList = LottoList.generateLottoList(manualNumbers, count);
        OutputView.printLottoLists(manualLottoAmount, count, lottoList);

        //지난주 로또 번호 받기
        List<Integer> winningNumbers = InputView.getLastWeekNumbers();

        //보너스볼 입력받기
        int bonusBallNumber = InputView.getBonusBallNumber();

        //각 로또와 winningnumber 비교해서 일치개수(matchCount)구하기
        Map<Prize, Integer> prizeCounts = new HashMap<>();
        for (Lotto lotto : lottoList.getLottoLists()) {
            Prize prize = calculatePrize(lotto, winningNumbers, bonusBallNumber);
            if (prize == null) continue;
            prizeCounts.put(prize, prizeCounts.getOrDefault(prize, 0) + 1);
        }

        //수익률 계산
        WinningStatistics statistics = new WinningStatistics(prizeCounts, money);

        //출력
        OutputView.printWinningStatics(statistics);
    }
}

