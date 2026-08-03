import controller.LottosGenerator;
import controller.Result;
import controller.WinFinder;
import model.Lotto;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        InputView inputView=new InputView();
        OutputView outputView=new OutputView();
        //구매금액입력
        int cost=inputView.inputCost();

        //수동로또 개수 입력
        int passiveAmount=inputView.inputPassiveAmount();

        //수동 개수만큼 로또 번호 입력
        List<Lotto> lottos= inputView.inputPassiveLotto(passiveAmount);

        //총갯수중 자동,수동 몇개인지 구분
        int totalAmount=cost/1000;
        int autoAmount=totalAmount-passiveAmount;

        //수동 몇장, 자동몇장 구매했는지 출력
        outputView.printAmount(autoAmount,passiveAmount);


        // 당첨-입력간 [중복갯수:해당 중복갯수가 몇장] 형식의 Map생성
        Map<Integer,Integer> staticWin;

        //각 lotto별 중복되는 숫자들의 List들을 모아놓은 List
        List<List<Integer>> matrix;

        //로직 담당 Controller 생성
        LottosGenerator lottosGenerator=new LottosGenerator(autoAmount,lottos);

        //수동로또 + 자동로또= 전체 로또 완성
        lottos=lottosGenerator.genLotto();

        //구매액만큼 로또 자동구매결과 출력
        outputView.printLottos(lottos);

        //당첨번호 입력
        List<Integer> winningNums = inputView.inputWinningNums();
        //보너스볼 입력
        int bonusBall=inputView.inputBonusBall();

        WinFinder winFinder=new WinFinder(winningNums,bonusBall);

        matrix=winFinder.findWinning(lottos,totalAmount);

        staticWin=winFinder.createWinCountMap(matrix);

        Result result=new Result();

        float ratio=result.totalRatio(cost,staticWin);

        outputView.cycle(staticWin);

        outputView.printRatio(ratio);
    }
}
