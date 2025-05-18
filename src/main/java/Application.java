import controller.LottoController;
import service.LottoGenerator;
import service.LottoNumberGenerator;
import service.LottoPurchaseService;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoGenerator lottoGenerator = new LottoGenerator(new LottoNumberGenerator());
        LottoPurchaseService purchaseService = new LottoPurchaseService(lottoGenerator);

        LottoController controller = new LottoController(inputView, outputView, purchaseService);
        controller.run();
    }
}
