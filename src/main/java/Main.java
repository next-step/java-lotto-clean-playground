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
        int cost=view.inputCost();
        int amount=view.printAmount(cost);
        Map<Integer,Integer> staticWin=new HashMap<>();
        List<Lotto> lottos;
        List<List<Integer>> matrix=new ArrayList<>();

        Controller controller=new Controller(amount);
        lottos=controller.genLotto();
        view.printLottos(lottos);
        List<Integer> winningNums = view.inputWinningNums();

        matrix=controller.findWinning(winningNums);
        System.out.println(matrix);
        ////////////////////////////////////////////////////////////////////////////////////////
        staticWin=controller.createWinCountMap(matrix);

        System.out.println("당첨통계");
        System.out.println("---------");

        float ratio=controller.totalRatio(cost,staticWin);

        view.cycle(staticWin);

        System.out.printf("총 수익률은 %f입니다",ratio);

    }
}
