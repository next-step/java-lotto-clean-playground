import controller.LottoController;
import view.InputView;

public class LottoApplication {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new InputView(System.in));
        lottoController.run();
    }
}
