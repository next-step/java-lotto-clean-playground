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

        //수동로또 개수 입력
        int passiveAmount=view.inputPassiveAmount();

        //수동 개수만큼 로또 번호 입력
        List<Lotto> lottos= view.inputPassiveLotto(passiveAmount);

        //총갯수중 자동,수동 몇개인지 구분
        int totalAmount=cost/1000;
        int autoAmount=totalAmount-passiveAmount;

        //수동 몇장, 자동몇장 구매했는지 출력
        view.printAmount(autoAmount,passiveAmount);


        // 당첨-입력간 중복갯수:몇개 형식의 Map생성
        Map<Integer,Integer> staticWin=new HashMap<>();

        //각 lotto별 중복되는 숫자들의 List들을 모아놓은 List
        List<List<Integer>> matrix=new ArrayList<>();

        //로직 담당 Controller 생성
        Controller controller=new Controller(autoAmount,passiveAmount);

        //수동로또 + 자동로또= 전체 로또 완성
        lottos.addAll(controller.genLotto());

        //구매액만큼 로또 자동구매결과 출력
        view.printLottos(lottos);

        //당첨번호 입력
        List<Integer> winningNums = view.inputWinningNums();
        //보너스볼 입력
        int bonusBall=view.inputBonusBall();

        matrix=controller.findWinning(lottos,winningNums,bonusBall);
        System.out.println(matrix); //[[1, 2, 3, 4, 5, 6], [3], [2], [5], [4], [], [], [6], [1], []]
        ////////////////////////////////////////////////////////////////////////////////////////
        staticWin=controller.createWinCountMap(matrix,bonusBall);
        System.out.println(staticWin); // {3=0, 4=0, 5=0, 6=0, 7=0}

        System.out.println("당첨통계");
        System.out.println("---------");

        float ratio=controller.totalRatio(cost,staticWin);

        view.cycle(staticWin);

        System.out.printf("총 수익률은 %f입니다",ratio);

    }
}
