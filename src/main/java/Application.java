import domain.LottoGame;
import domain.PurchasePrice;
import view.InputView;
import view.ResultView;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        final int price = InputView.getPurchasePrice();
        final int manualLottoCount = InputView.getManualLottoCount();

        final PurchasePrice purchasePrice = new PurchasePrice(price, manualLottoCount);
        final List<String> manualLottoNumbers = InputView.getManualLottoNumber(manualLottoCount);

        LottoGame lottoGame = new LottoGame(purchasePrice, manualLottoNumbers);

        ResultView.printLottoNumberCount(purchasePrice.getManualLottoCount(), purchasePrice.getAutoLottoCount());
        ResultView.printLotto(lottoGame.getLottos());

        final String[] values = InputView.getCorrectLotto();
        final int bonusBall = InputView.getBonusBall();
        lottoGame.createCorrectLotto(values, bonusBall);

        ResultView.printWinningStatistics(lottoGame.getRanksCount(), lottoGame.calculateProfit(purchasePrice));
    }
}
