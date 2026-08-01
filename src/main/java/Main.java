import domain.Lottos;
import domain.PurchaseManage;
import view.InputView;
import view.ResultView;

import static view.InputView.getPurchaseAmount;

public class Main {
    public static void main(String[] args) {
        int price = InputView.getPurchaseAmount();

        PurchaseManage purchaseManage = new PurchaseManage();
        Lottos lottos = purchaseManage.buyLottos(price);
        ResultView.showNum(lottos);
    }
}
