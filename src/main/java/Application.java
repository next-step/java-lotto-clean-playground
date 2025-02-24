import domain.Lottos;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class Application
{
    public static void main (String[] args)
    {
        // 구입 금액 입력 및 상점에 지불
        int totalCost = InputView.getTotalCost();
        final Lottos tickets = new Lottos();
        tickets.payCost(totalCost);

        // 수동 로또 입력 처리 및 생성
        int manualBuyCount = InputView.getManualBuyCount();
        List<List<Integer>> manualLottos = InputView.getManualLottoNumber(manualBuyCount);

        tickets.buyManualLottos(manualBuyCount, manualLottos);

        // 자동 로또 구입 및 번호 생성
        tickets.buyAutoLottos();

        // 현재 생성한 로또 개수 및 번호 출력
        ResultView.printBuyLottoCount(tickets.getLottoCount());
        ResultView.printLottos(tickets.getLottos());

        // 지난주 로또 번호를 입력받기
        List<Integer> lastweekGoalNumber = InputView.getLastweekGoalNumber();
        int lastweekBonusGoalNumber = InputView.getLastweekBonusGoalNumber();

        // 결과 출력
        ResultView.calculateProceed(lastweekGoalNumber, lastweekBonusGoalNumber, tickets);

    }
}
