import domain.Lottos;
import domain.PurchaseManage;
import domain.WinningNumber;
import view.InputView;
import view.ResultView;

public class Main {
    public static void main(String[] args) {
        int price = InputView.getPurchaseAmount();

        PurchaseManage purchaseManage = new PurchaseManage();
        Lottos lottos = purchaseManage.buyLottos(price);
        ResultView.showNum(lottos);

        String enteredWinningNumber = InputView.getWinningNumber();
        WinningNumber winningNumber = new WinningNumber();
        winningNumber.setWinningNumber(enteredWinningNumber);
    }
}
