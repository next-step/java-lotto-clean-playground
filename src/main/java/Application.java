import view.InputView;
import view.ResultView;
import domain.Lotto;


public class Application {
    public static void main(String[] args) {
        Lotto lotto = new Lotto();

        int price = InputView.inputPrice();
        price = lotto.calculateCount(price);

        ResultView.printPurchase(price);
    }
}
