import controller.LottosGenerator;
import controller.PassiveLottoGenerator;
import controller.Result;
import controller.WinCounter;
import controller.WinningLotto;
import model.Lotto;
import model.Rank;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        //구매금액입력
        int cost = inputView.inputCost();

        //수동로또 개수 입력
        int passiveAmount = inputView.inputPassiveAmount();

        PassiveLottoGenerator passiveLottoGenerator = new PassiveLottoGenerator();
        //수동 개수만큼 로또 번호 입력
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < passiveAmount; i++) {
            List<Integer> nums = inputView.inputPassiveLotto();
            Lotto lotto = passiveLottoGenerator.genPassiveLotto(nums);
            lottos.add(lotto);
        }

        //총갯수중 자동,수동 몇개인지 구분
        int totalAmount = cost / 1000;
        int autoAmount = totalAmount - passiveAmount;

        //수동 몇장, 자동몇장 구매했는지 출력
        outputView.printAmount(autoAmount, passiveAmount);

        //로직 담당 Controller 생성
        LottosGenerator lottosGenerator = new LottosGenerator();

        //수동로또 + 자동로또= 전체 로또 완성
        lottos.addAll(lottosGenerator.genLottos(autoAmount));

        //구매액만큼 로또 자동구매결과 출력
        outputView.printLottos(lottos);

        //당첨번호 입력
        List<Integer> winningNums = inputView.inputWinningNums();
        //보너스볼 입력
        int bonusBall = inputView.inputBonusBall();
        Lotto winLotto = new Lotto(winningNums);

        WinningLotto winningLotto = new WinningLotto(winLotto, bonusBall);
        WinCounter winCounter = new WinCounter();

        EnumMap<Rank, Integer> counts = winCounter.countWins(lottos, winningLotto);

        Result result = new Result(counts);

        float ratio = result.totalRatio(cost);

        outputView.cycle(counts);

        outputView.printRatio(ratio);
    }
}
