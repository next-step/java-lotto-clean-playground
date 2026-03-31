import domain.Money;
import domain.RandomLottoGenerator;
import view.InputView;
import view.ResultView;

public class Application {

    public void run() {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        RandomLottoGenerator random = new RandomLottoGenerator();
        final int amount = inputView.getMoney();
        Money money = new Money(amount);
        final int number = money.getNumber();
        resultView.printPurchaseCount(number);
        for (int i = 0; i < number; i++){
            resultView.printLottoNumbers(random.generate());
        }

    }

    public static void main(String[] args) {
        Application lotto = new Application();
        lotto.run();
    }
}
