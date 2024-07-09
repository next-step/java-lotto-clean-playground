import java.util.ArrayList;
import java.util.List;

public class LottoApp {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        LottoMachine lottoMachine = new LottoMachine();

        int purchaseAmount = inputHandler.getPurchaseAmount();
        int totalLottos = lottoMachine.calculateNumberOfLottos(purchaseAmount);

        int manualLottoCount = inputHandler.getManualLottoCount();
        List<Lotto> manualLottos = inputHandler.getManualLottos(manualLottoCount);

        int automaticLottoCount = totalLottos - manualLottoCount;
        List<Lotto> automaticLottos = lottoMachine.generateAutomaticLottos(automaticLottoCount);

        List<Lotto> allLottos = new ArrayList<>();
        allLottos.addAll(manualLottos);
        allLottos.addAll(automaticLottos);

        System.out.println("수동으로 " + manualLottoCount + "장, 자동으로 " + automaticLottoCount + "개를 구매했습니다.");
        for (Lotto lotto : allLottos) {
            System.out.println(lotto);
        }

        WinningNumbers winningNumbers = inputHandler.getWinningNumbers();
        LottoGameResult gameResult = new LottoGameResult(allLottos, winningNumbers);
        gameResult.printWinningStatistics();
    }
}
