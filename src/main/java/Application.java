import controller.LottoVendingMachine;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoVendingMachine lottoVendingMachine = new LottoVendingMachine(
                new InputView(),
                new OutputView()
        );
        lottoVendingMachine.run();
    }
}
