import domain.Lotto;
import domain.LottoStore;
import domain.Lottos;
import domain.Money;
import view.InputView;
import view.ResultView;

public class Application {
    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final LottoStore lottoStore = new LottoStore();

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    private void run() {
        while(true) {
            try{
                int price = inputView.inputPrice();

                Lottos lottos = lottoStore.buy(new Money(price));

                resultView.printLottos(lottos);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }


        }
    }
}
