import controller.LottoController;
import domain.LottoShop;
import generator.RandomNumberGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoShop lottoShop = new LottoShop(new RandomNumberGenerator());

        LottoController lottoController = new LottoController(inputView, outputView, lottoShop);
        lottoController.run();
    }
}
