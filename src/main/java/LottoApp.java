import java.util.List;

public class LottoApp {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        LottoMachine lottoMachine = new LottoMachine();

        int purchaseAmount = inputHandler.getPurchaseAmount();
        int numberOfLottos = lottoMachine.calculateNumberOfLottos(purchaseAmount);
        System.out.println(numberOfLottos + "개를 구매했습니다.");

        List<Lotto> lottos = lottoMachine.generateLottos(numberOfLottos);
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }

        WinningNumbers winningNumbers = inputHandler.getWinningNumbers();
        LottoGameResult gameResult = new LottoGameResult(lottos, winningNumbers);
        gameResult.printWinningStatistics();
    }
}
