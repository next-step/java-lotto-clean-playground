import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        LottoGameController lottoGameController = new LottoGameController(new InputView(), new OutputView());

        lottoGameController.run();
    }
}
