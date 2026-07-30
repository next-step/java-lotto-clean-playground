import controller.Controller;
import model.Lotto;
import view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        View view=new View();
        //구매금액입력
        int cost=view.inputCost();
        int amount=view.printAmount(cost);
        Map<Integer,Integer> staticWin=new HashMap<>();
        List<Lotto> lottos;
        List<List<Integer>> matrix=new ArrayList<>();

        Controller controller=new Controller(amount);
        lottos=controller.genLotto();
        //구매액만큼 로또 자동구매결과 출력
        view.printLottos(lottos);
        //당첨번호 입력
        List<Integer> winningNums = view.inputWinningNums();
        //보너스볼 입력
        int bonusBall=view.inputBonusBall();

        matrix=controller.findWinning(winningNums,bonusBall);
        ////////////////////////////////////////////////////////////////////////////////////////
        staticWin=controller.createWinCountMap(matrix,bonusBall);

        System.out.println("당첨통계");
        System.out.println("---------");

        float ratio=controller.totalRatio(cost,staticWin);

        view.cycle(staticWin);

        System.out.printf("총 수익률은 %f입니다",ratio);

    }
}
