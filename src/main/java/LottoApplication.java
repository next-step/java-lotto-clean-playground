import controller.LottoController;
import domain.generator.AutoLottoGenerator;
import domain.generator.LottoGenerator;
import domain.generator.ManualLottoGenerator;
import view.InputView;
import view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoController controller = new LottoController(inputView, outputView);
        controller.run();
    }
}
