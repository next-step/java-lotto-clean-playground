import domain.LottoGame;
import domain.PurchasePrice;
import view.InputView;
import view.ResultView;

public class Application {
    public static void main(String[] args) {
        final PurchasePrice purchasePrice = InputView.getPurchasePrice();
        LottoGame lottoGame = new LottoGame(purchasePrice);
        ResultView.printLottoNumberCount(purchasePrice.getLottoNumberCount());
        ResultView.printLotto(lottoGame.getLottos());

        final String[] values = InputView.getCorrectLotto();
        lottoGame.createCorrectLotto(values);

        ResultView.printWinningStatistics(lottoGame.correctCount(), lottoGame.calculateProfit());
    }
}
